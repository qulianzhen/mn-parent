package com.mn.syspermission.controller;

import com.mn.commonbean.restful.Message;
import com.mn.mnutil.MessageUtil;
import com.mn.mnutil.SnowFlake;
import com.mn.module.page.PageQuerier;
import com.mn.syspermission.entity.param.SysPermissionParam;
import com.mn.syspermission.entity.param.SysRolePermissionParam;
import com.mn.syspermission.entity.po.SysPermission;
import com.mn.syspermission.service.SysPermissionService;
import com.mn.util.PageUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:     SysPermissionController.java
 * @Description:   权限表的Controller层
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-15 12:35:14
 */
@RestController
@RequestMapping("/api/sysPermission")
public class SysPermissionController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SysPermissionController.class);
	
	@Autowired
	private SysPermissionService sysPermissionService;
	@Autowired
    private SnowFlake snowFlake;



	@PostMapping(value = "/sysPermissionTree")
	@ApiOperation(value = "查询权限树")
	public Message listTree(SysPermissionParam sysPermissionParam){
		return MessageUtil.successMsg(sysPermissionService.listTree(sysPermissionParam));
	}
	
	/**
	 * @description 查询权限表列表-分页
	 * @author qlz
     * @param param 分页查询参数
	 * @return 权限表列表
	 */
	@PostMapping(value = "/listPage")
	public Message sysPermissionList(@RequestBody PageQuerier<SysPermissionParam> param) {
	
		if(param == null){
			param = new PageQuerier<>();
		}
        return PageUtil.easyUIPageSuccessMsg(sysPermissionService.listPage(param));
	}

	/**
	 * @description 查询权限表列表-不分页
	 * @author qlz
     * @param param 查询参数
	 * @return 权限表列表
	 */
	@PostMapping(value = "/list")
	public Message sysPermissionList(@RequestBody SysPermissionParam param) {
		if(param == null){
			param = new SysPermissionParam();
		}
        return MessageUtil.successMsg(sysPermissionService.list(param));
	}
	
	/**
	* @description 保存(新增/修改)[权限表]
	* @author qlz
    * @param param 保存参数
	* @return 响应对象
	*/
	@PostMapping(value = "/save")
	public Message saveSysPermission(@RequestBody SysPermissionParam param) {
		if(param!=null &&(param.getId()==null || "".equals(param.getId()))){
            param.setId(snowFlake.nextId());
			sysPermissionService.insert(param);
        }else{
			sysPermissionService.update(param);
        }
        return MessageUtil.successMsg(param.getId());
	}
		
	
	/**
	* @description 批量删除权限表
	* @author qlz
	* @param ids 参数:主键集合
	* @return 响应对象
	*/
	@PostMapping(value = "/delete")
	public Message deleteSysPermission(@RequestBody List<Long> ids) {
		sysPermissionService.delete(ids);
		return MessageUtil.successMsg();
	}
	
	
	 /**
	* @description 获取单个权限表
	* @author qlz
	* @param id 参数:主键
	* @return 响应对象
	*/
	@GetMapping(value = "/get")
	public Message getSysPermission(Long id) {
		SysPermission sysPermission = sysPermissionService.get(id);
		return MessageUtil.successMsg(sysPermission);
	}

	@PostMapping(value = "/getSysPermissionInfo")
	@ApiOperation(value = "根据菜单id获取菜单信息")
	public Message getSysPermissionInfo(Long id){
		return MessageUtil.successMsg(sysPermissionService.getSysPermissionInfo(id));
	}

	/**
	 * @description 用户角色绑定表
	 * @author qlz
	 * @param param
	 * @return 响应对象
	 */
	@PostMapping(value = "/saveRolePermission")
	public Message saveUserRole(@RequestBody List<SysRolePermissionParam> param) {
		sysPermissionService.saveRolePermission(param);
		return MessageUtil.successMsg();
	}

	/**
	 * @description 根据角色ID获取多个权限
	 * @author qlz
	 * @param roleId 参数:角色Id
	 * @return 响应对象
	 */
	@GetMapping(value = "/getPermissionsByRoleId")
	public Message getPermissionsByRoleId(Long roleId) {
		List<Long> permissionIds = sysPermissionService.getSysPermissionIdByRoleId(roleId);
		return MessageUtil.successMsg(permissionIds);
	}
}