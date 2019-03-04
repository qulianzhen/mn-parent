package com.mn.commonbean.restful;

import java.io.Serializable;

/**
 * 
 * @description 消息
 * @author gujianting
 *
 */
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final int UNKNOWN_EXCEPTION = -99;//未知异常

	public static final int SUCCESS = 0;//成功

	public static final int BUSINESS_EXCEPTION = 1;//通用业务异常
	
	public static final int INVALID_PARAM = 2;//无效参数
	
	public static final int INCORRECT_OPERATION = 3;//不恰当的操作

	public static final int NO_PERMISSION = 4;//无权限(备用)

	public static final int NO_LOGIN = 5;//未登陆(备用)

	
	/**
	 * 返回的信息(主要出错的时候使用)
	 */
	private String msg = "success";

	/**
	 * 接口返回码, 0表示成功, 其他看对应的定义
	 * 推荐的做法是: 
	 * 0   : 成功
	 * >0 : 表示已知的异常(例如提示错误等, 需要调用地方单独处理) 
	 * <0 : 表示未知的异常(不需要单独处理, 调用方统一处理)
	 */
	private int code = SUCCESS;
	
	/**
	 * 返回的数据
	 */
	private Object data;
    
	/**
	 * 初始化一个新创建的 Message 对象，使其表示一个空消息。
	 */
	public Message(){
		super();
	}
	
	/**
	 *  初始化一个新创建的 Message 对象，用code和msg
	 * @param code
	 * @param msg
	 */
	public Message(int code,String msg){
		super();
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 *  初始化一个新创建的 Message 对象，用三个参数
	 * @param code
	 * @param msg
	 * @param data
	 */
	public Message(int code,String msg,Object data){
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	/*链式调用*/
	public Message msg(String msg){
		this.msg = msg;
		return this;
	}
	public Message code(int code){
		this.code = code;
		return this;
	}
	public Message data(Object data){
		this.data = data;
		return this;
	}
}