package com.mn.commonbean.exception;

/**
 * 业务异常--参数无效（如，必填参数为空）
 * @author qlz
 *
 */
public class BusinessInvalidParamException extends BusinessException {

	private static final long serialVersionUID = 7815256992613940640L;

	public BusinessInvalidParamException() {
		super();
	}

	public BusinessInvalidParamException(String message) {
		super(message);
	}

	public BusinessInvalidParamException(Throwable cause) {
		super(cause);
	}

	public BusinessInvalidParamException(String message, Throwable cause) {
		super(message, cause);
	}

}
