package com.mn.entity;

/**
 * @ClassName:     SysUser.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         33107
 * @version        V1.0  
 * @Date           2016-06-14 09:49:04
 */
public class SysUser{
	//用户ID
	private Long userId;
	//登录名
	private String username;
	//密码
	private String password;
	//用户姓名
	private String name;
	//权限
	private String rights;
	//角色
	private String roleId;
	//最后登录时间
	private String lastLogin;
	//IP
	private String ip;
	//状态
	private String status;
	//备注
	private String bz;
	//皮肤
	private String skin;
	//邮箱
	private String email;
	//
	private String number;
	//电话
	private String phone;
	//set get method
	public Long getUserId(){
		return userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getRights(){
		return rights;
	}
	
	public void setRights(String rights){
		this.rights = rights;
	}
	
	public String getRoleId(){
		return roleId;
	}
	
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
	
	public String getLastLogin(){
		return lastLogin;
	}
	
	public void setLastLogin(String lastLogin){
		this.lastLogin = lastLogin;
	}
	
	public String getIp(){
		return ip;
	}
	
	public void setIp(String ip){
		this.ip = ip;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getBz(){
		return bz;
	}
	
	public void setBz(String bz){
		this.bz = bz;
	}
	
	public String getSkin(){
		return skin;
	}
	
	public void setSkin(String skin){
		this.skin = skin;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getNumber(){
		return number;
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
}