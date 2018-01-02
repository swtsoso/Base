package com.voting.common.exception;

import java.io.Serializable;

/**
 * 系统异常
 * @version 1.0
 */
public final class SystemException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1030931998999674664L;

	public SystemException() {
		super();
	}

	public SystemException(Exception e) {
		super(e);
	}

	public SystemException(String message) {
		super(message);
	}
}