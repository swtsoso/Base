package com.voting.common.exception;

import java.io.Serializable;

/**
 * 业务异常
 * @version 1.0
 */
public class BusinessException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1030931998999674664L;

	private Integer errorType = 0;

	public BusinessException() {
		super();
	}

	public BusinessException(Exception e) {
		super(e);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message , Integer errorType){
		super(message);
		this.errorType = errorType;
	}

	public Integer getErrorType() {
		return errorType;
	}

	public void setErrorType(Integer errorType) {
		this.errorType = errorType;
	}
}