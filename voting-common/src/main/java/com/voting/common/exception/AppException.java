package com.voting.common.exception;

import java.io.Serializable;

public class AppException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2613149337703494097L;
	
	public AppException(){}
	
	public AppException(String msg) {
		super(msg);
	}
	
	public AppException(Exception e) {
		super(e);
	}

}
