package com.mn.sysrole.entity.param;

import com.mn.sysrole.entity.po.SysUserRole;

import java.io.Serializable;

/**
 * @ClassName:     SysUserRoleParam.java
 * @Description:   用户角色表绑定的参数类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-04 21:44:06
 */
public class SysUserRoleParam extends SysUserRole implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer clearAllRoleFlag;

	public Integer getClearAllRoleFlag() {
		return clearAllRoleFlag;
	}

	public void setClearAllRoleFlag(Integer clearAllRoleFlag) {
		this.clearAllRoleFlag = clearAllRoleFlag;
	}
	//查询条件...
}