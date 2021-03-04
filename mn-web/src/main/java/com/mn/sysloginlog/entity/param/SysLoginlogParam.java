package com.mn.sysloginlog.entity.param;
import java.io.Serializable;
/**
 * @ClassName:     SysLoginlogParam.java
 * @Description:   登录日志的参数类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-07 17:32:43
 */
public class SysLoginlogParam implements Serializable{
	private static final long serialVersionUID = 1L;

	private String loginlogUserName;

	private String startTime;

	private String endTime;

	public String getLoginlogUserName() {
		return loginlogUserName;
	}

	public void setLoginlogUserName(String loginlogUserName) {
		this.loginlogUserName = loginlogUserName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}