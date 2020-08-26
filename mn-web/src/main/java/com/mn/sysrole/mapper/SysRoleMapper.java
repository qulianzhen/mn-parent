package com.mn.sysrole.mapper;
import com.mn.commonbean.tkmapper.MyMapper;
import com.mn.sysrole.entity.param.SysRoleParam;
import com.mn.sysrole.entity.po.SysRole;
import com.mn.sysrole.entity.po.SysUserRole;

import java.util.List;

/**
 * @ClassName:     SysRoleMapper.java
 * @Description:   角色表的mapper执行类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-04 21:44:06
 */
public interface SysRoleMapper extends MyMapper<SysRole>{
    /**
     * 查询角色表记录
     * @param param
     * @return
     */
    List<SysRole> listSysRole(SysRoleParam param);

    /**
    * 新增角色表记录
    * @param sysRole
    */
    void insertSysRole(SysRole sysRole);

    /**
    * 修改角色表记录
    * @param sysRole
    */
    void updateSysRole(SysRole sysRole);

    /**
    * 查询单条角色表记录
    * @param id
    * @return
    */
    SysRole getSysRole(Long id);

    /**
    * 批量删除角色表记录
    * @param ids
    */
    void deleteSysRole(List<Long> ids);

    /**
     * 根据用户Id删除角色绑定
     * @param userId
     */
    void deleteUserRole(Long userId);

    /**
     * 批量新增用户-角色绑定关系
     * @param sysUserRoles
     */
    void saveUserRole(List<SysUserRole> sysUserRoles);

    /**
     * 根据用户Id获取角色ID集合
     * @param userId
     * @return
     */
    List<Long> listRoleByUserId(Long userId);
}