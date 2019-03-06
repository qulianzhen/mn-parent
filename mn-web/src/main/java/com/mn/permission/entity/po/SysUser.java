package com.mn.permission.entity.po;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
/**
 * @ClassName:     SysUser.java
 * @Description:   用户表的实体Bean
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2019-03-01 01:19:52
 */
@ApiModel(value="用户表Model",description="用户表对象数据模型")
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	@ApiModelProperty(notes = "主键")
	@Column(name="ID")
	private Long id;
	//登陆名
	@ApiModelProperty(notes = "登陆名")
	@Column(name="USER_NAME")
	private String userName;
	//昵称
	@ApiModelProperty(notes = "昵称")
	@Column(name="NICK_NAME")
	private String nickName;
	//密码
	@ApiModelProperty(notes = "密码")
	@Column(name="PASSWORD")
	private String password;
	//对应员工的ID
	@ApiModelProperty(notes = "对应员工的ID")
	@Column(name="EMPLOYYER_ID")
	private String employyerId;
	//是否锁定 0否 1是
	@ApiModelProperty(notes = "是否锁定 0否 1是")
	@Column(name="ISLOCK")
	private Integer islock;
	//头像
	@ApiModelProperty(notes = "头像")
	@Column(name="PHOTO")
	private String photo;
	//用户状态，备用
	@ApiModelProperty(notes = "用户状态，备用")
	@Column(name="USER_STATUS")
	private Integer userStatus;
	//备注
	@ApiModelProperty(notes = "备注")
	@Column(name="REMARK")
	private String remark;
	//创建时间
	@ApiModelProperty(hidden=true)
	@Column(name="CREATE_DATE")
	private Date createDate;
	//修改时间
	@ApiModelProperty(hidden=true)
	@Column(name="UPDATE_DATE")
	private Date updateDate;
	//是否删除：0未删除，1删除
	@ApiModelProperty(hidden=true)
	@Column(name="IS_DELETED")
	private Integer isDeleted;
	//最后登录时间
	@ApiModelProperty(notes = "最后登录时间")
	@Column(name="LAST_LOGIN_DATE")
	private Date lastLoginDate;
	//set get method
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getNickName(){
		return nickName;
	}
	
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getEmployyerId(){
		return employyerId;
	}
	
	public void setEmployyerId(String employyerId){
		this.employyerId = employyerId;
	}
	
	public Integer getIslock(){
		return islock;
	}
	
	public void setIslock(Integer islock){
		this.islock = islock;
	}
	
	public String getPhoto(){
		return photo;
	}
	
	public void setPhoto(String photo){
		this.photo = photo;
	}
	
	public Integer getUserStatus(){
		return userStatus;
	}
	
	public void setUserStatus(Integer userStatus){
		this.userStatus = userStatus;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public Date getCreateDate(){
		return createDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public Date getUpdateDate(){
		return updateDate;
	}
	
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	
	public Integer getIsDeleted(){
		return isDeleted;
	}
	
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}
	
	public Date getLastLoginDate(){
		return lastLoginDate;
	}
	
	public void setLastLoginDate(Date lastLoginDate){
		this.lastLoginDate = lastLoginDate;
	}
	
}