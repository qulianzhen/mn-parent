package com.mn.sysrole.controller;

import com.mn.commonbean.restful.Message;
import com.mn.mnutil.MessageUtil;
import com.mn.mnutil.SnowFlake;
import com.mn.module.page.PageQuerier;
import com.mn.sysrole.entity.param.SysRoleParam;
import com.mn.sysrole.entity.param.SysUserRoleParam;
import com.mn.sysrole.entity.po.SysRole;
import com.mn.sysrole.service.SysRoleService;
import com.mn.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:     SysRoleController.java
 * @Description:   角色表的Controller层
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-04 21:44:06
 */
@RestController
@RequestMapping("/api/sysRole")
public class SysRoleController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);
	
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
    private SnowFlake snowFlake;
	
	/**
	 * @description 查询角色表列表-分页
	 * @author qlz
     * @param param 分页查询参数
	 * @return 角色表列表
	 */
	@PostMapping(value = "/listPage")
	public Message sysRoleList(@RequestBody PageQuerier<SysRoleParam> param) {
	
		if(param == null){
			param = new PageQuerier<>();
		}
        return PageUtil.easyUIPageSuccessMsg(sysRoleService.listPage(param));
	}

	/**
	 * @description 查询角色表列表-不分页
	 * @author qlz
     * @param param 查询参数
	 * @return 角色表列表
	 */
	@PostMapping(value = "/list")
	public Message sysRoleList(@RequestBody SysRoleParam param) {
		if(param == null){
			param = new SysRoleParam();
		}
        return MessageUtil.successMsg(sysRoleService.list(param));
	}
	
	/**
	* @description 保存(新增/修改)[角色表]
	* @author qlz
    * @param param 保存参数
	* @return 响应对象
	*/
	@PostMapping(value = "/save")
	public Message saveSysRole(@RequestBody SysRoleParam param) {
		if(param!=null &&(param.getId()==null || "".equals(param.getId()))){
            param.setId(snowFlake.nextId());
			sysRoleService.insert(param);
        }else{
			sysRoleService.update(param);
        }
        return MessageUtil.successMsg(param.getId());
	}
		
	
	/**
	* @description 批量删除角色表
	* @author qlz
	* @param ids 参数:主键集合
	* @return 响应对象
	*/
	@PostMapping(value = "/delete")
	public Message deleteSysRole(@RequestBody List<Long> ids) {
		sysRoleService.delete(ids);
		return MessageUtil.successMsg();
	}
	
	
	 /**
	* @description 获取单个角色表
	* @author qlz
	* @param id 参数:主键
	* @return 响应对象
	*/
	@GetMapping(value = "/get")
	public Message getSysRole(Long id) {
		SysRole sysRole = sysRoleService.get(id);
		return MessageUtil.successMsg(sysRole);
	}


	/**
	 * @description 用户角色绑定表
	 * @author qlz
	 * @param param
	 * @return 响应对象
	 */
	@PostMapping(value = "/saveUserRole")
	public Message saveUserRole(@RequestBody List<SysUserRoleParam> param) {
		sysRoleService.saveUserRole(param);
		return MessageUtil.successMsg();
	}

	/**
	 * @description 根据用户ID获取多个角色
	 * @author qlz
	 * @param userId 参数:用户Id
	 * @return 响应对象
	 */
	@GetMapping(value = "/getRolesByUserId")
	public Message getSysRoleIdByUserId(Long userId) {
		List<Long> roleIds = sysRoleService.getSysRoleIdByUserId(userId);
		return MessageUtil.successMsg(roleIds);
	}
}