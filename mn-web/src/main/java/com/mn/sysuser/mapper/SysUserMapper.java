package com.mn.sysuser.mapper;
import com.mn.commonbean.tkmapper.MyMapper;
import com.mn.sysuser.entity.param.SysUserParam;
import com.mn.sysuser.entity.po.SysUser;

import java.util.List;

/**
 * @ClassName:     SysUserMapper.java
 * @Description:   用户表的mapper执行类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-07 00:20:30
 */
public interface SysUserMapper extends MyMapper<SysUser>{
    /**
     * 查询用户表记录
     * @param param
     * @return
     */
    List<SysUser> listSysUser(SysUserParam param);

    /**
    * 新增用户表记录
    * @param sysUser
    */
    void insertSysUser(SysUser sysUser);

    /**
    * 修改用户表记录
    * @param sysUser
    */
    void updateSysUser(SysUser sysUser);

    /**
    * 查询单条用户表记录
    * @param id
    * @return
    */
    SysUser getSysUser(Long id);

    /**
    * 批量删除用户表记录
    * @param ids
    */
    void deleteSysUser(List<Long> ids);
    /**
     * 根据用户名获取操作权限List
     * @param loginName
     * @return
     */
    List<String> getUrlPermitByUserName(String loginName);
}