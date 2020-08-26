package com.mn.syspermission.entity.po;

import java.io.Serializable;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/8/23-23:47
 * @Version 1.0
 * @Modified By:
 */
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    private Long id;
    private Long roleId;
    private Long permissionId;
    private Integer permissionType;


}
