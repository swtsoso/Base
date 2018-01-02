package com.voting.web.result;

import java.util.HashMap;
import java.util.Map;

import com.voting.common.utils.ReflectUtils;

/**
 * 请求基础结果
 * @version 1.0
 */
public class RequestBaseResult {

	/**
	 * 请求返回状态
	 * @version 1.0
	 */
	public interface ResponseStatus {
		/**
		 * 成功
		 */
		int SUCCESS = 1;
		/**
		 * 异常
		 */
		int EXCEPTION = -1;
		/**
		 * 无权限
		 */
		int NOPERMISSION = -2;
		/**
		 * 失败
		 */
		int FAIL = -3;
		/**
		 * 参数错误
		 */
		int PARAMETER_ERROR = -4;
	}

	/**
	 * 返回结果名称
	 */
	public static final String RESULT_NAME = "result";
	/**
	 * 返回状态
	 */
	private int status = ResponseStatus.SUCCESS;

	/**
	 * 返回数据
	 */
	private Map<String, Object> data = new HashMap<>();

	/**
	 * {@linkplain #data}
	 */
	public void addData(String key, Object value) {
		this.data.put(key, value);
	}

	/**
	 * {@linkplain #data}
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * {@linkplain #status}
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * {@linkplain #data}
	 */
	public void setDataFromMap(Map data) {
		this.data = data;
	}

	/**
	 * {@linkplain #data}
	 */
	public void setDataFromObject(Object object) {
		this.data = ReflectUtils.toMap(object);
	}

	/**
	 * {@linkplain #status}
	 */
	public void setStatus(int status) {
		this.status = status;
	}

}
