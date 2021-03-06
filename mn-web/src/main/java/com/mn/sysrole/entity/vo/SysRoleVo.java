package com.mn.sysrole.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName:     SysRoleVo.java
 * @Description:   角色表的Vo类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-04 21:44:06
 */
public class SysRoleVo implements Serializable{
	private static final long serialVersionUID = 1L;
	//主键
	private String id;
	//角色名
	private String roleName;
	//是否可用 0可用 1 不可用
	private Integer roleEnable;
	//备注
	private String remark;
	//创建时间
	private Date createDate;
	//修改时间
	private Date updateDate;
	//是否删除：0未删除，1删除
	private Integer isDeleted;
	//set get method
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getRoleName(){
		return roleName;
	}

	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	public Integer getRoleEnable(){
		return roleEnable;
	}

	public void setRoleEnable(Integer roleEnable){
		this.roleEnable = roleEnable;
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
}