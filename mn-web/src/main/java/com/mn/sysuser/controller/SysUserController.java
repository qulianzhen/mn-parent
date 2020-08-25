package com.mn.sysuser.controller;

import com.mn.commonbean.restful.Message;
import com.mn.mnutil.MessageUtil;
import com.mn.mnutil.SnowFlake;
import com.mn.module.page.PageQuerier;
import com.mn.sysuser.entity.param.SysUserParam;
import com.mn.sysuser.entity.po.SysUser;
import com.mn.sysuser.service.SysUserService;
import com.mn.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:     SysUserController.java
 * @Description:   用户表的Controller层
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-07 00:20:30
 */
@RestController
@RequestMapping("/api/sysUser")
public class SysUserController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
    private SnowFlake snowFlake;
	
	/**
	 * @description 查询用户表列表-分页
	 * @author qlz
     * @param param 分页查询参数
	 * @return 用户表列表
	 */
	@PostMapping(value = "/listPage")
	public Message sysUserList(@RequestBody PageQuerier<SysUserParam> param) {
	
		if(param == null){
			param = new PageQuerier<>();
		}
        return PageUtil.easyUIPageSuccessMsg(sysUserService.listPage(param));
	}

	/**
	 * @description 查询用户表列表-不分页
	 * @author qlz
     * @param param 查询参数
	 * @return 用户表列表
	 */
	@PostMapping(value = "/list")
	public Message sysUserList(@RequestBody SysUserParam param) {
		if(param == null){
			param = new SysUserParam();
		}
        return MessageUtil.successMsg(sysUserService.list(param));
	}
	
	/**
	* @description 保存(新增/修改)[用户表]
	* @author qlz
    * @param param 保存参数
	* @return 响应对象
	*/
	@PostMapping(value = "/save")
	public Message saveSysUser(@RequestBody SysUserParam param) {
		if(param!=null &&(param.getId()==null || "".equals(param.getId()))){
            param.setId(snowFlake.nextId());
			sysUserService.insert(param);
        }else{
			sysUserService.update(param);
        }
        return MessageUtil.successMsg(param.getId());
	}
		
	
	/**
	* @description 批量删除用户表
	* @author qlz
	* @param ids 参数:主键集合
	* @return 响应对象
	*/
	@PostMapping(value = "/delete")
	public Message deleteSysUser(@RequestBody List<Long> ids) {
		sysUserService.delete(ids);
		return MessageUtil.successMsg();
	}
	
	
	 /**
	* @description 获取单个用户表
	* @author qlz
	* @param id 参数:主键
	* @return 响应对象
	*/
	@GetMapping(value = "/get")
	public Message getSysUser(Long id) {
		SysUser sysUser = sysUserService.get(id);
		return MessageUtil.successMsg(sysUser);
	}
}