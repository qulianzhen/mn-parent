package com.mn.permission.mapper;

import com.mn.commonbean.tkmapper.MyMapper;
import com.mn.permission.entity.po.SysUser;

import java.util.List;

public interface SysUserMapper extends MyMapper<SysUser> {

    /**
     * 根据用户名获取操作权限List
     * @param loginName
     * @return
     */
    List<String> getUrlPermitByUserName(String loginName);
}