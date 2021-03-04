package com.mn.sysinfo.entity.vo;

import java.io.Serializable;

/**
 * @ClassName:     SysInfoVo.java
 * @Description:   系统信息的Vo类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-09 23:52:45
 */
public class SysInfoVo implements Serializable{
	private static final long serialVersionUID = 1L;

	private String osName;
	private String osArch;

	private String userName;
	private String userLan;
	private String userTimeZone;
	private String userHome;

	private String javaVersion;
	private String javaHome;
	private String javaVmMode;
	private String javaVmName;

    private String tomcatMaxMem;
    private String tomcatUsageMem;
    private String tomcatUptime;
    private String tomcatMaxThreadCount;
	private String tomcatUsageThreadCount;

	private String pId;


	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsArch() {
		return osArch;
	}

	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLan() {
		return userLan;
	}

	public void setUserLan(String userLan) {
		this.userLan = userLan;
	}

	public String getUserTimeZone() {
		return userTimeZone;
	}

	public void setUserTimeZone(String userTimeZone) {
		this.userTimeZone = userTimeZone;
	}

	public String getUserHome() {
		return userHome;
	}

	public void setUserHome(String userHome) {
		this.userHome = userHome;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	public String getJavaHome() {
		return javaHome;
	}

	public void setJavaHome(String javaHome) {
		this.javaHome = javaHome;
	}

	public String getJavaVmMode() {
		return javaVmMode;
	}

	public void setJavaVmMode(String javaVmMode) {
		this.javaVmMode = javaVmMode;
	}

	public String getTomcatMaxMem() {
		return tomcatMaxMem;
	}

	public void setTomcatMaxMem(String tomcatMaxMem) {
		this.tomcatMaxMem = tomcatMaxMem;
	}

	public String getTomcatUsageMem() {
		return tomcatUsageMem;
	}

	public void setTomcatUsageMem(String tomcatUsageMem) {
		this.tomcatUsageMem = tomcatUsageMem;
	}

	public String getTomcatUptime() {
		return tomcatUptime;
	}

	public void setTomcatUptime(String tomcatUptime) {
		this.tomcatUptime = tomcatUptime;
	}

	public String getTomcatMaxThreadCount() {
		return tomcatMaxThreadCount;
	}

	public void setTomcatMaxThreadCount(String tomcatMaxThreadCount) {
		this.tomcatMaxThreadCount = tomcatMaxThreadCount;
	}

	public String getTomcatUsageThreadCount() {
		return tomcatUsageThreadCount;
	}

	public void setTomcatUsageThreadCount(String tomcatUsageThreadCount) {
		this.tomcatUsageThreadCount = tomcatUsageThreadCount;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getJavaVmName() {
		return javaVmName;
	}

	public void setJavaVmName(String javaVmName) {
		this.javaVmName = javaVmName;
	}
}