package com.mn.mnutil;

import com.mn.commonbean.exception.CodeMsgInfo;
import com.mn.commonbean.restful.Message;


/**
 * 
 * @description 消息
 * @author qlz
 *
 */
public class  MessageUtil{
	
	public static final String COMMON_SUCCESS_MSG = "操作成功";//通用成功消息
	public static final String COMMON_FAILED_MSG = "操作失败";//通用失败消息
	
	/**
	 * 创建一个[无返回数据][成功]的message
	 * @return
	 */
	public static  Message  successMsg(){
		Message message = new Message();
		message.setCode(Message.SUCCESS);
		message.setMsg(COMMON_SUCCESS_MSG);
		return message;
	}
	
	/**
	 * 创建一个[无返回数据][成功]的message
	 * @param msg 消息
	 * @return
	 */
	public static  Message  successMsg(String msg){
		Message message = successMsg();
		message.setMsg(msg);
		return message;
	}

	/**
	 * 创建一个[有特定异常编码的][异常]的message
	 * @param code 异常编码
	 * @return
	 */
	public static Message codeMsg(int code){
		Message message = errorMsg(code).msg(CodeMsgInfo.codeMsgMap.get(code));
		return message;
	}
	
	/**
	 * 创建一个[有返回数据][成功]的message
	 * @param data 数据
	 * @return
	 */
	public  static   Message successMsg(Object data){
		Message message = successMsg();
		message.setData(data);
		return message;
	}
	
	/**
	 * 创建一个[有返回数据][成功]的message
	 * @param msg 消息
	 * @param data 数据
	 * @return
	 */
	public  static   Message successMsg(String msg,Object data){
		Message message = successMsg(msg);
		message.setData(data);
		return message;
	}
	
	/**
	 * 创建一个失败的的Message
	 * @param msg  消息
	 * @param code 异常编码
	 * @return
	 */
	public  static   Message errorMsg(String msg,int code){
		Message message = new Message();
		message.setCode(code);
		message.setMsg(msg);
		return message;
	}
	/**
	 * 创建一个失败的的Message
	 * @param code  异常编码
	 * @return
	 */
	public  static  Message errorMsg(int code){
		Message message = new Message();
		message.setCode(code);
		message.setMsg(COMMON_FAILED_MSG);
		return message;
	}
}