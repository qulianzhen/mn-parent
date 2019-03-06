package com.mn.commonbean.exception;


/**
 * 业务异常--提供详细异常码的异常（如，110010 - 砍价活动已开启，不能执行编辑操作!）
 * @author qlz
 *
 */
public class BusinessCodeException extends BusinessException {


	private static final long serialVersionUID = 4385157744761519437L;
    
	private Integer code;
	
	public BusinessCodeException(Integer code) {
		super("["+code+"]:"+ CodeMsgInfo.codeMsgMap.get(code));
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
