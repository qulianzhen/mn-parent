package com.mn.sysloginlog.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
/**
 * @ClassName:     SysLoginlog.java
 * @Description:   登录日志的实体Bean
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-07 17:32:43
 */
public class SysLoginlog implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	private Long id;
	//登录名
	private String loginlogUserName;
	//ip
	private String loginlogIp;
	//登录时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date loginlogDate;
	//set get method
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getLoginlogUserName(){
		return loginlogUserName;
	}
	
	public void setLoginlogUserName(String loginlogUserName){
		this.loginlogUserName = loginlogUserName;
	}
	
	public Date getLoginlogDate(){
		return loginlogDate;
	}
	
	public void setLoginlogDate(Date loginlogDate){
		this.loginlogDate = loginlogDate;
	}
	public String getLoginlogIp() {
		return loginlogIp;
	}

	public void setLoginlogIp(String loginlogIp) {
		this.loginlogIp = loginlogIp;
	}
}