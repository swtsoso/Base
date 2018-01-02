package com.voting.common.exception;

import java.io.Serializable;

/**
 * 系统参数异常
 * @version 1.0
 */
public final class SystemParamException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1030931998999674664L;

	public static final SystemParamException DEFALUT_NULL_OR_EMPTY_EXCETION = new SystemParamException("参数为空！");

	public static SystemParamException getNullOrEmptyException(String message) {
		return new SystemParamException(message);
	}

	public SystemParamException() {
		super();
	}

	public SystemParamException(String message) {
		super(message);
	}

	public SystemParamException(Throwable e) {
		super(e);
	}
}