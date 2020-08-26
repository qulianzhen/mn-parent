package com.mn.sysrole.service;

import com.github.pagehelper.PageInfo;
import com.mn.module.page.PageQuerier;
import com.mn.sysrole.entity.param.SysRoleParam;
import com.mn.sysrole.entity.param.SysUserRoleParam;
import com.mn.sysrole.entity.po.SysRole;
import java.util.List;

/**
 * @ClassName:     SysRoleService.java
 * @Description:   角色表的服务接口
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-04 21:44:06
 */
public interface SysRoleService {
	
	/**
	* @description 分页查询角色表
	* @param pageQuerierParam 分页查询条件
	* @return
	* @author qlz
	*/
	public PageInfo<SysRole> listPage(PageQuerier<SysRoleParam> pageQuerierParam);

	/**
	* @description 非分页查询角色表
	* @param param 查询条件
	* @return 对象集合
	* @author qlz
	*/
	public List<SysRole> list(SysRoleParam param);
	
	/**
	* @description 新增角色表
	* @param param 角色表参数对象
	* @author qlz
	*/
	public void insert(SysRoleParam param);

	/**
	* @description 修改角色表
	* @param param 角色表参数对象
	* @author qlz
	*/
	public void update(SysRoleParam param);
	
	/**
	* @description 根据ID查找角色表
	* @param id  角色表的id 	
	* @return 角色表对象
	* @author qlz
	*/
	public SysRole get(Long id);
	
	
	/**
	* @description 删除角色表
	* @param ids  id集合 	
	* @author qlz
	*/
	public void delete(List<Long> ids);

	/**
	 * 批量保存用户和角色的绑定关系
	 * @param param
	 */
    void saveUserRole(List<SysUserRoleParam> param);

	/**
	 * 根据用户Id获取角色ID集合
	 * @param userId
	 * @return
	 */
	List<Long> getSysRoleIdByUserId(Long userId);
}