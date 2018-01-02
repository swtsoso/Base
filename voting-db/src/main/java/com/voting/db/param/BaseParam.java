package com.voting.db.param;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.voting.common.exception.ParamException.ParamExceptionBuilder;

/**
 * 基础参数
 * @version 1.0
 */
public class BaseParam {

	/**
	 * 请求IP
	 */
	private String requestIp;
	/**
	 * 请求sessionId
	 * 优先从cookie中取
	 */
	private String requestSessionId;
	/**
	 * 请求用户
	 */
	private Object requestUser;
	/**
	 * 请求用户ID
	 */
	private Long requestUserId;
	/**
	 * 请求用户类型
	 */
	private Integer requestUserType;
	/**
	 * 请求用户名称
	 */
	private String requestUsername;
	/**
	 * 请求用户登录名
	 */
	private String requestLoginname;
	/**
	 * 请求用户岗位ID
	 */
	private Long requestUserReqartersId;
	/**
	 * 请求用户权限机构
	 */
	private List<Long> requestUserOrgIdList;
	/**
	 * 请求用户权限港口
	 */
	private List<Long> requestUserPortIdList;
	/**
	 * 是否验证过
	 */
	private boolean isChecked = false;
	
	/**
	 * app token
	 */
	private String token;
	
	/**
	 * 供应商登陆，供应商类型
	 */
	private Integer supplierType;

	public Integer getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}

	/**
	 * {@linkplain #requestIp}
	 */
	public String getRequestIp() {
		return requestIp;
	}

	/**
	 * {@linkplain #requestIp}
	 */
	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	/**
	 * {@linkplain #requestSessionId}
	 */
	public String getRequestSessionId() {
		return requestSessionId;
	}

	/**
	 * {@linkplain #requestSessionId}
	 */
	public void setRequestSessionId(String requestSessionId) {
		this.requestSessionId = requestSessionId;
	}

	/**
	 * {@linkplain #requestUser}
	 */
	public Object getRequestUser() {
		return requestUser;
	}

	/**
	 * {@linkplain #requestUser}
	 */
	public void setRequestUser(Object requestUser) {
		this.requestUser = requestUser;
	}

	/**
	 * {@linkplain #requestUserId}
	 */
	public Long getRequestUserId() {
		return requestUserId;
	}

	/**
	 * {@linkplain #requestUserId}
	 */
	public void setRequestUserId(Long requestUserId) {
		this.requestUserId = requestUserId;
	}

	/**
	 * {@linkplain #requestUserType}
	 */
	public Integer getRequestUserType() {
		return requestUserType;
	}

	/**
	 * {@linkplain #requestUserType}
	 */
	public void setRequestUserType(Integer requestUserType) {
		this.requestUserType = requestUserType;
	}

	/**
	 * {@linkplain #requestUsername}
	 */
	public String getRequestUsername() {
		return requestUsername;
	}

	/**
	 * {@linkplain #requestUsername}
	 */
	public void setRequestUsername(String requestUsername) {
		this.requestUsername = requestUsername;
	}

	/**
	 * {@linkplain #requestLoginname}
	 */
	public String getRequestLoginname() {
		return requestLoginname;
	}

	/**
	 * {@linkplain #requestLoginname}
	 */
	public void setRequestLoginname(String requestLoginname) {
		this.requestLoginname = requestLoginname;
	}

	/**
	 * {@linkplain #requestUserReqartersId}
	 */
	public Long getRequestUserReqartersId() {
		return requestUserReqartersId;
	}

	/**
	 * {@linkplain #requestUserReqartersId}
	 */
	public void setRequestUserReqartersId(Long requestUserReqartersId) {
		this.requestUserReqartersId = requestUserReqartersId;
	}

	/**
	 * {@linkplain #requestUserOrgIdList}
	 */
	public List<Long> getRequestUserOrgIdList() {
		return requestUserOrgIdList;
	}

	/**
	 * {@linkplain #requestUserOrgIdList}
	 */
	public void setRequestUserOrgIdList(List<Long> requestUserOrgIdList) {
		this.requestUserOrgIdList = requestUserOrgIdList;
	}

	/**
	 * {@linkplain #requestUserPortIdList}
	 */
	public List<Long> getRequestUserPortIdList() {
		return requestUserPortIdList;
	}

	/**
	 * {@linkplain #requestUserPortIdList}
	 */
	public void setRequestUserPortIdList(List<Long> requestUserPortIdList) {
		this.requestUserPortIdList = requestUserPortIdList;
	}
	
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 参数验证<p>
	 * 如果参数isChecked为true，则跳过验证，否则进行验证，验证完之后将isChecked设为true<p>
	 * 当参数验证不通过时，则随机抛出第一个参数异常
	 */
	public final <T> void validParam(T parameter, Class<?>... groups) {
//		if (isChecked) {
//			return;
//		}
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(parameter, groups);
		isChecked = true;
		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			ParamExceptionBuilder peb = new ParamExceptionBuilder(constraintViolation.getMessage());
			peb.setFieldValue(constraintViolation.getInvalidValue());
			peb.setFieldName(constraintViolation.getPropertyPath().toString());
			peb.setObjectName(constraintViolation.getRootBeanClass().getSimpleName());
			throw peb.builder();
		}
	}
	
	public void setBaseParam(BaseParam baseParam){
		requestIp = baseParam.requestIp;
		requestSessionId = baseParam.requestSessionId;
		requestUser = baseParam.requestUser;
		requestUserId = baseParam.requestUserId;
		requestUserType = baseParam.requestUserType;
		requestUsername = baseParam.requestUsername;
		requestLoginname = baseParam.requestLoginname;
		requestUserReqartersId = baseParam.requestUserReqartersId;
		requestUserOrgIdList = baseParam.requestUserOrgIdList;
		requestUserPortIdList = baseParam.requestUserPortIdList;
	}

	/**
	 * ID组
	 */
	public interface IdGroup {
	}

	/**
	 * 新增组
	 */
	public interface InsertGroup {
	}
	
	/**
	 * 更新组
	 */
	public interface UpdateGroup{
	}

	public interface BatchGroup{

	}

}
