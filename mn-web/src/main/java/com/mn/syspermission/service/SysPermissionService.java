package com.mn.syspermission.service;

import com.github.pagehelper.PageInfo;
import com.mn.menu.entity.vo.SysMenuTreeVo;
import com.mn.module.page.PageQuerier;
import com.mn.syspermission.entity.param.SysPermissionParam;
import com.mn.syspermission.entity.param.SysRolePermissionParam;
import com.mn.syspermission.entity.po.SysPermission;
import com.mn.syspermission.entity.vo.SysPermissionVo;

import java.util.List;

/**
 * @ClassName:     SysPermissionService.java
 * @Description:   权限表的服务接口
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-15 12:35:14
 */
public interface SysPermissionService {
	
	/**
	* @description 分页查询权限表
	* @param pageQuerierParam 分页查询条件
	* @return
	* @author qlz
	*/
	public PageInfo<SysPermission> listPage(PageQuerier<SysPermissionParam> pageQuerierParam);

	/**
	* @description 非分页查询权限表
	* @param param 查询条件
	* @return 对象集合
	* @author qlz
	*/
	public List<SysPermission> list(SysPermissionParam param);
	
	/**
	* @description 新增权限表
	* @param param 权限表参数对象
	* @author qlz
	*/
	public void insert(SysPermissionParam param);

	/**
	* @description 修改权限表
	* @param param 权限表参数对象
	* @author qlz
	*/
	public void update(SysPermissionParam param);
	
	/**
	* @description 根据ID查找权限表
	* @param id  权限表的id 	
	* @return 权限表对象
	* @author qlz
	*/
	public SysPermission get(Long id);
	
	
	/**
	* @description 删除权限表
	* @param ids  id集合 	
	* @author qlz
	*/
	public void delete(List<Long> ids);

	/**
	 * 权限树
	 * @param sysPermissionParam
	 * @return
	 */
	List<SysMenuTreeVo> listTree(SysPermissionParam sysPermissionParam);

	/**
	 * 查询权限信息
	 * @param id
	 * @return
	 */
	SysPermissionVo getSysPermissionInfo(Long id);

	/**
	 * 保存角色权限绑定关系
	 * @param param
	 */
    void saveRolePermission(List<SysRolePermissionParam> param);

	/**
	 * 根据角色ID查询权限
	 * @param roleId
	 * @return
	 */
	List<Long> getSysPermissionIdByRoleId(Long roleId);
}