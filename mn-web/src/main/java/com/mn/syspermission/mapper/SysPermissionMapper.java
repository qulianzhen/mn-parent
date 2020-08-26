package com.mn.syspermission.mapper;
import com.mn.commonbean.tkmapper.MyMapper;
import com.mn.syspermission.entity.param.SysPermissionParam;
import com.mn.syspermission.entity.po.SysPermission;
import com.mn.syspermission.entity.po.SysRolePermission;
import com.mn.syspermission.entity.vo.SysPermissionVo;

import java.util.List;

/**
 * @ClassName:     SysPermissionMapper.java
 * @Description:   权限表的mapper执行类
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-15 12:35:14
 */
public interface SysPermissionMapper extends MyMapper<SysPermission>{
    /**
     * 查询权限表记录
     * @param param
     * @return
     */
    List<SysPermission> listSysPermission(SysPermissionParam param);

    /**
    * 新增权限表记录
    * @param sysPermission
    */
    void insertSysPermission(SysPermission sysPermission);

    /**
    * 修改权限表记录
    * @param sysPermission
    */
    void updateSysPermission(SysPermission sysPermission);

    /**
    * 查询单条权限表记录
    * @param id
    * @return
    */
    SysPermission getSysPermission(Long id);

    /**
    * 批量删除权限表记录
    * @param ids
    */
    void deleteSysPermission(List<Long> ids);

    /**
     * 查询权限信息
     * @param id
     * @return
     */
    SysPermissionVo getSysPermissionInfo(Long id);

    /**
     * 根据角色id删除权限
     * @param roleId
     */
    void deleteRolePermission(Long roleId);

    /**
     * 批量添加角色权限绑定关系
     * @param sysRolePermissions
     */
    void saveRolePermission(List<SysRolePermission> sysRolePermissions);

    /**
     * 根据角色ID获取权限
     * @param roleId
     * @return
     */
    List<Long> listPermissionByRoleId(Long roleId);
}