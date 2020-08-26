package com.mn.syspermission.entity.vo;

import java.io.Serializable;

/**
 * @ClassName:     SysPermissionVo.java
 * @Description:   权限表的Vo类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-15 12:35:14
 */
public class SysPermissionVo implements Serializable{
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
	//显示序号
	private Integer permissionOrder;
	//所属菜单名称
	private String menuName;
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

	public Integer getPermissionOrder(){
		return permissionOrder;
	}

	public void setPermissionOrder(Integer permissionOrder){
		this.permissionOrder = permissionOrder;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
}