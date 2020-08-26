package com.mn.menu.entity.param;

import com.mn.menu.entity.po.SysMenu;
import com.mn.syspermission.entity.po.SysPermission;

import java.util.List;
import java.util.Map;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/7/3-1:37
 * @Version 1.0
 * @Modified By:
 */
public class SysMenuParam extends SysMenu {
    /**
     * 菜单-权限对应
     */
    private Map<String,List<SysPermission>> permissionMap;

    /**
     * 登录用户名
     */
    private String loginName;

    public Map<String, List<SysPermission>> getPermissionMap() {
        return permissionMap;
    }

    public void setPermissionMap(Map<String, List<SysPermission>> permissionMap) {
        this.permissionMap = permissionMap;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
