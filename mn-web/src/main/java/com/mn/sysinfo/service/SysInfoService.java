package com.mn.sysinfo.service;

import com.mn.sysinfo.entity.vo.SysInfoVo;

/**
 * @ClassName:     SysInfoService.java
 * @Description:   系统信息的服务接口
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-09 23:52:45
 */
public interface SysInfoService {

	/**
	* @description 获取系统信息
	* @return 系统信息对象
	* @author qlz
	*/
	public SysInfoVo get();

}