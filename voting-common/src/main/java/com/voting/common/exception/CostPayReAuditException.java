package com.voting.common.exception;

public class CostPayReAuditException extends BusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CostPayReAuditException() {
		super();
	}

	public CostPayReAuditException(Exception e) {
		super(e);
	}

	public CostPayReAuditException(String message) {
		super(message);
	}
}
