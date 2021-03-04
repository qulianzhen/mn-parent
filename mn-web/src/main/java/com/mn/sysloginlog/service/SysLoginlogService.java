package com.mn.sysloginlog.service;

import com.github.pagehelper.PageInfo;
import com.mn.module.page.PageQuerier;
import com.mn.sysloginlog.entity.param.SysLoginlogParam;
import com.mn.sysloginlog.entity.po.SysLoginlog;

/**
 * @ClassName:     SysLoginlogService.java
 * @Description:   登录日志的服务接口
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-07 17:32:43
 */
public interface SysLoginlogService {
	
	/**
	* @description 分页查询登录日志
	* @param pageQuerierParam 分页查询条件
	* @return
	* @author qlz
	*/
	public PageInfo<SysLoginlog> listPage(PageQuerier<SysLoginlogParam> pageQuerierParam);

	/**
	* @description 新增登录日志
	* @param param 登录日志参数对象
	* @author qlz
	*/
	public void insert(SysLoginlogParam param);
}