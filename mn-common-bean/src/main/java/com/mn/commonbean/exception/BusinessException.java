package com.mn.commonbean.exception;

/**
 * 业务异常父类（AOP可统一捕捉:异常不确定时使用）
 * @author qlz
 *
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 7595823944201239363L;
	
	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

}
