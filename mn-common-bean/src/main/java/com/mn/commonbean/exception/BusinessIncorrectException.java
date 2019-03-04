package com.mn.commonbean.exception;

/**
 * 业务异常--不正确的操作(如，某种状态的数据不允许删除)
 * @author qlz
 *
 */
public class BusinessIncorrectException extends BusinessException {

	private static final long serialVersionUID = 7815256992613940640L;

	public BusinessIncorrectException() {
		super();
	}

	public BusinessIncorrectException(String message) {
		super(message);
	}

	public BusinessIncorrectException(Throwable cause) {
		super(cause);
	}

	public BusinessIncorrectException(String message, Throwable cause) {
		super(message, cause);
	}

}
