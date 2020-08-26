package com.mn.syspermission.entity.param;

import com.mn.syspermission.entity.po.SysRolePermission;

import java.io.Serializable;

/**
 * @ClassName:     SysUserRoleParam.java
 * @Description:   角色权限表绑定的参数类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-04 21:44:06
 */
public class SysRolePermissionParam extends SysRolePermission implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer clearAllPermissionFlag;

	public Integer getClearAllPermissionFlag() {
		return clearAllPermissionFlag;
	}

	public void setClearAllPermissionFlag(Integer clearAllPermissionFlag) {
		this.clearAllPermissionFlag = clearAllPermissionFlag;
	}

	//查询条件...
}