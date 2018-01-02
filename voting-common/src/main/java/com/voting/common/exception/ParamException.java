package com.voting.common.exception;

/**
 * 业务参数异常
 * @version 1.0
 */
public final class ParamException extends RuntimeException {

	public static class ParamExceptionBuilder {

		/**
		 * 描述
		 */
		private String message;

		/**
		 * 参数名称
		 */
		private String fieldName;
		/**
		 * 参数值
		 */
		private Object fieldValue;
		/**
		 * 对象名称
		 */
		private String objectName;

		public ParamExceptionBuilder(String message) {
			this.message = message;
		}

		public ParamException builder() {
			return new ParamException(this);
		}

		/**
		 * {@linkplain #fieldName}
		 */
		public ParamExceptionBuilder setFieldName(String fieldName) {
			this.fieldName = fieldName;
			return this;
		}

		/**
		 * {@linkplain #fieldValue}
		 */
		public ParamExceptionBuilder setFieldValue(Object fieldValue) {
			this.fieldValue = fieldValue;
			return this;
		}

		/**
		 * {@linkplain #message}
		 */
		public ParamExceptionBuilder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * {@linkplain #objectName}
		 */
		public ParamExceptionBuilder setObjectName(String objectName) {
			this.objectName = objectName;
			return this;
		}

	}

	private static final long serialVersionUID = 1030931998999674664L;

	/**
	 * 参数名称
	 */
	private final String fieldName;
	/**
	 * 参数值
	 */
	private final Object fieldValue;

	/**
	 * 对象名称
	 */
	private final String objectName;

	private ParamException(ParamExceptionBuilder builder) {
		super(builder.message);
		this.fieldName = builder.fieldName;
		this.fieldValue = builder.fieldValue;
		this.objectName = builder.objectName;
	}

	/**
	 * {@linkplain #fieldName}
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * {@linkplain #fieldValue}
	 */
	public Object getFieldValue() {
		return fieldValue;
	}

	/**
	 * {@linkplain #objectName}
	 */
	public String getObjectName() {
		return objectName;
	}

}