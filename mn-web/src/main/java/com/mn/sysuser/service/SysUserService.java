package com.mn.sysuser.service;

import com.github.pagehelper.PageInfo;
import com.mn.module.page.PageQuerier;
import com.mn.sysuser.entity.param.SysUserParam;
import com.mn.sysuser.entity.po.SysUser;

import java.util.List;
import java.util.Set;

/**
 * @ClassName:     SysUserService.java
 * @Description:   用户表的服务接口
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-07 00:20:30
 */
public interface SysUserService {
	
	/**
	* @description 分页查询用户表
	* @param pageQuerierParam 分页查询条件
	* @return
	* @author qlz
	*/
	public PageInfo<SysUser> listPage(PageQuerier<SysUserParam> pageQuerierParam);

	/**
	* @description 非分页查询用户表
	* @param param 查询条件
	* @return 对象集合
	* @author qlz
	*/
	public List<SysUser> list(SysUserParam param);
	
	/**
	* @description 新增用户表
	* @param param 用户表参数对象
	* @author qlz
	*/
	public void insert(SysUserParam param);

	/**
	* @description 修改用户表
	* @param param 用户表参数对象
	* @author qlz
	*/
	public void update(SysUserParam param);
	
	/**
	* @description 根据ID查找用户表
	* @param id  用户表的id 	
	* @return 用户表对象
	* @author qlz
	*/
	public SysUser get(Long id);
	/**
	 * @description 根据登录名查找用户表
	 * @param loginName  用户登录名
	 * @return 用户表对象
	 * @author qlz
	 */
	public SysUser get(String loginName);
	
	
	/**
	* @description 删除用户表
	* @param ids  id集合 	
	* @author qlz
	*/
	public void delete(List<Long> ids);

	/**
	 * 根据用户名获取用户Bean
	 * @param userName 登录用户名
	 * @return
	 */
	SysUser findSysUserByUserName(String userName);

	/**
	 * 根据用户id获取权限Url
	 * @param id
	 * @return
	 */
	Set<String> getUrlPermitByUserId(Long id);
}