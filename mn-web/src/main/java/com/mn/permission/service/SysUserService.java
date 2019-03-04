package com.mn.permission.service;

import com.mn.permission.entity.po.SysUser;

import java.util.Set;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2019/3/1-1:25
 * @Version 1.0
 * @Modified By:
 */
public interface SysUserService {
    /**
     * 根据用户名获取用户Bean
     * @param userName 登录用户名
     * @return
     */
    SysUser findSysUserByUserName(String userName);

    /**
     * 根据用户名获取权限Url
     * @param username
     * @return
     */
    Set<String> getUrlPermitByUserName(String username);
}
