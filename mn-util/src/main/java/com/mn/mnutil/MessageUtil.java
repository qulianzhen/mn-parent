package com.mn.mnutil;

import com.mn.commonbean.restful.Message;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * 
 * @description 消息
 * @author qlz
 *
 */
public class  MessageUtil{
	
	public static final String COMMON_SUCCESS_MSG = "操作成功";//通用成功消息
	public static final String COMMON_FAILED_MSG = "操作失败";//通用失败消息
	
	public static final Map<Integer,String> codeMsgMap = new HashMap<>();
	//将业务异常码配置读入内存
	static{
		 Properties prop = new Properties();
	        InputStream in = MessageUtil.class.getResourceAsStream("/businesscode.properties");
	        try {
	            prop.load(in);
	            Set<Object> keySet = prop.keySet();
	            for(Object key:keySet){
	            	codeMsgMap.put(Integer.parseInt(key.toString()), new String(((String) prop.get(key)).getBytes("ISO-8859-1"), "UTF-8") );
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (in != null) in.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}

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