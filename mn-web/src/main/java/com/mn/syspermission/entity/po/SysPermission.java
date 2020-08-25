package com.mn.syspermission.entity.po;
import java.io.Serializable;
import java.util.Date;
/**
 * @ClassName:     SysPermission.java
 * @Description:   权限表的实体Bean
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-15 12:35:14
 */
public class SysPermission implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	private Long id;
	//权限名称
	private String permissionName;
	//权限URL
	private String permissionUrl;
	//0操作url 1页面元素
	private Integer permissionType;
	//上级ID
	private String permissionParentId;
	//备注
	private String permissionRemark;
	//创建时间
	private Date createDate;
	//修改时间
	private Date updateDate;
	//是否删除：0未删除，1删除
	private Integer isDeleted;
	//显示序号
	private Integer permissionOrder;
	//set get method
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getPermissionName(){
		return permissionName;
	}
	
	public void setPermissionName(String permissionName){
		this.permissionName = permissionName;
	}
	
	public String getPermissionUrl(){
		return permissionUrl;
	}
	
	public void setPermissionUrl(String permissionUrl){
		this.permissionUrl = permissionUrl;
	}
	
	public Integer getPermissionType(){
		return permissionType;
	}
	
	public void setPermissionType(Integer permissionType){
		this.permissionType = permissionType;
	}
	
	public String getPermissionParentId(){
		return permissionParentId;
	}
	
	public void setPermissionParentId(String permissionParentId){
		this.permissionParentId = permissionParentId;
	}
	
	public String getPermissionRemark(){
		return permissionRemark;
	}
	
	public void setPermissionRemark(String permissionRemark){
		this.permissionRemark = permissionRemark;
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
	
	public Integer getPermissionOrder(){
		return permissionOrder;
	}
	
	public void setPermissionOrder(Integer permissionOrder){
		this.permissionOrder = permissionOrder;
	}
	
}