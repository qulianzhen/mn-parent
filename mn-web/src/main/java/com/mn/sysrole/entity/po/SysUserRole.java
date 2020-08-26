package com.mn.sysrole.entity.po;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mn.config.JsonLongSerializer;

import java.io.Serializable;

/**
 * @ClassName:     SysUserRole.java
 * @Description:   用户角色表绑定表的实体Bean
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-04 21:44:06
 */
public class SysUserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	//主键
	@JsonSerialize(using = JsonLongSerializer.class )
	private Long id;
	//用户ID
	@JsonSerialize(using = JsonLongSerializer.class )
	private Long userId;
	//角色ID
	private Long roleId;
	//set get method
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}