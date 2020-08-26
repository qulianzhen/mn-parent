package com.mn.syspermission.entity.vo;

import com.mn.syspermission.entity.po.SysPermission;

import java.util.List;

/**
 * @Description: 菜单-权限集合VO
 * @Author:Mloong
 * @Date :create in 2020/7/3-1:36
 * @Version 1.0
 * @Modified By:
 */
public class SysPermissionMenuVo {

    private String permissionParentId;
    private List<SysPermission> permissonList;

    public String getId() {
        return permissionParentId;
    }

    public void setId(String id) {
        this.permissionParentId = id;
    }


    public List<SysPermission> getPermissonList() {
        return permissonList;
    }

    public void setPermissonList(List<SysPermission> permissonList) {
        this.permissonList = permissonList;
    }
}
