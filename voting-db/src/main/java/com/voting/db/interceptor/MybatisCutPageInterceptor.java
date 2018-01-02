package com.voting.db.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.jdbc.ConnectionLogger;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;

import com.voting.common.exception.SystemException;
import com.voting.common.utils.ReflectUtils;
import com.voting.db.param.PageParam;

@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class MybatisCutPageInterceptor implements Interceptor {

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		@Override
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

	private enum DataBaseType {
		ORACLE {
			@Override
			public String getLimitSql(String sql, PageParam<?> pageBean) {
				StringBuilder pageSql = new StringBuilder(100);
				String beginrow = String.valueOf((pageBean.getPage() - 1) * pageBean.getPageSize());
				String endrow = String.valueOf(pageBean.getPage() * pageBean.getPageSize());
				pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
				pageSql.append(sql);
				pageSql.append(" ) temp where rownum <= ").append(endrow);
				pageSql.append(") where row_id > ").append(beginrow);
				return pageSql.toString();
			}
		},
		MYSQL {
			@Override
			public String getLimitSql(String sql, PageParam<?> pageBean) {
				StringBuilder pageSql = new StringBuilder(100);
				String beginrow = String.valueOf((pageBean.getPage() - 1) * pageBean.getPageSize());
				pageSql.append(sql);
				pageSql.append(" limit " + beginrow + "," + pageBean.getPageSize());
				return pageSql.toString();
			}
		};

		/**
		 * 初始化数据库类型
		 */
		public static DataBaseType initDataBaseType(String dataBaseType) {
			if ("ORACLE".equalsIgnoreCase(dataBaseType)) {
				return ORACLE;
			} else if ("MYSQL".equalsIgnoreCase(dataBaseType)) {
				return MYSQL;
			} else {
				throw new SystemException("不被支持的数据库类型");
			}
		}

		/**
		 * 获取切分语句
		 */
		public abstract String getLimitSql(String sql, PageParam<?> pageBean);
	}

	private DataBaseType dataBaseType;

	private String countSQLSuffix;

	protected HashSet<String> sqlNames = new HashSet<>();

	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null) {
			for (String keyProperty : ms.getKeyProperties()) {
				builder.keyProperty(keyProperty);
			}
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		return builder.build();
	}

	/**
	 * 进行切分
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object doCutPage(Invocation invocation, Object parameter, PageParam pageBean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Configuration configuration = mappedStatement.getConfiguration();
		BoundSql boundSql = mappedStatement.getBoundSql(pageBean);
		if (sqlNames.isEmpty()) {
			this.initStatementMap(configuration);
		}
		if(pageBean.getIsCut()){
			pageBean.setCount(this.getCount(invocation, configuration, mappedStatement, boundSql, pageBean));
		}

		String limitSql = getLimitSql(boundSql, pageBean);
		BoundSql dataBoundSql = new BoundSql(mappedStatement.getConfiguration(), limitSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
		if (ReflectUtils.getFieldValue(boundSql, "metaParameters") != null) {
			MetaObject mo = (MetaObject) ReflectUtils.getFieldValue(boundSql, "metaParameters");
			ReflectUtils.setFieldValue(dataBoundSql, "metaParameters", mo);
		}
		MappedStatement DataMappedStatement = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(dataBoundSql));
		invocation.getArgs()[0] = DataMappedStatement;
		Object object = invocation.proceed();
		if (object instanceof List) {
			pageBean.setData((List<?>) object);
			if(!pageBean.getIsCut() && pageBean.getData() != null){
				pageBean.setCount(pageBean.getData().size());
			}
		}
		return object;
	}

	/**
	 * 执行统计SQL
	 */
	protected int exeCountSql(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Connection connection, Object parameter) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalSize = 0;
		try {
			ParameterHandler handler = configuration.newParameterHandler(mappedStatement, parameter, boundSql);
			stmt = connection.prepareStatement(this.getCountSql(boundSql.getSql()));
			handler.setParameters(stmt);
			rs = stmt.executeQuery();
			if (rs.next()) {
				totalSize = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		}
		return totalSize;
	}

	/**
	 * 执行查询
	 */
	private Object exeProceed(Invocation invocation, MappedStatement statement) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		invocation.getArgs()[0] = statement;
		return invocation.proceed();
	}

	/**
	 * 获取数据库连接
	 */
	private Connection getConnection(Transaction transaction, Log statementLog) throws SQLException {
		Connection connection = transaction.getConnection();
		if (statementLog.isDebugEnabled()) {
			return ConnectionLogger.newInstance(connection, statementLog, 1);
		} else {
			return connection;
		}
	}

	/**
	 * 获取结果总数
	 */
	private int getCount(Invocation invocation, Configuration configuration, MappedStatement statement, BoundSql boundSql, Object parameter) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
		int count = 0;
		String countSqlId = "";
		if(statement.getId().endsWith(countSQLSuffix)){
			countSqlId = statement.getId();
		} else {
			countSqlId = statement.getId() + countSQLSuffix;
		}
		if (sqlNames.contains(countSqlId)) {
			List<?> data = (List<?>) exeProceed(invocation, statement.getConfiguration().getMappedStatement(countSqlId));
			if (data.size() > 0) {
//				count = Integer.parseInt(data.get(0).toString());
				count = data.size();
			}
		} else {
			Executor ext = (Executor) invocation.getTarget();
			Connection conn = getConnection(ext.getTransaction(), statement.getStatementLog());
			count = exeCountSql(configuration, statement, boundSql, conn, parameter);
		}
		return count;
	}

	/**
	 * 获取统计语句
	 */
	private String getCountSql(String sql) {
		return "SELECT COUNT(1) FROM (" + sql + ") T";
	}

	/**
	 * 获取切分SQL
	 */
	private String getLimitSql(BoundSql boundSql, PageParam<?> pageBean) {
		if (!pageBean.getIsCut()) {
			return boundSql.getSql();
		}
		return dataBaseType.getLimitSql(boundSql.getSql(), pageBean);
	}

	/**
	 * 获取分页参数
	 */
	private PageParam<?> getPageBean(Object[] parameter) {
		for (Object obj : parameter) {
			if (obj instanceof PageParam) {
				return (PageParam<?>) obj;
			}
		}
		return null;
	}

	/**
	 * 初始化SQL语句
	 */
	private synchronized void initStatementMap(Configuration configuration) {
		if (!sqlNames.isEmpty()) {
			return;
		}
		Collection<String> statements = configuration.getMappedStatementNames();
		for (String name : statements) {
			sqlNames.add(name);
		}
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] parameter = invocation.getArgs();
		PageParam<?> page = getPageBean(parameter);
		if (page == null) {
			return invocation.proceed();
		} else {
			return this.doCutPage(invocation, parameter, page);
		}
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		String dataBaseType = properties.getProperty("dataBaseType");
		this.dataBaseType = DataBaseType.initDataBaseType(dataBaseType);
		this.countSQLSuffix = properties.getProperty("countSQLSuffix");
		this.countSQLSuffix = this.countSQLSuffix == null ? "Count" : this.countSQLSuffix;
	}
}
