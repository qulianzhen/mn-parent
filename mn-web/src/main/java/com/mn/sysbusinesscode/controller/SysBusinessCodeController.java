package com.mn.sysbusinesscode.controller;

import com.mn.commonbean.restful.Message;
import com.mn.mnutil.MessageUtil;
import com.mn.module.page.PageQuerier;
import com.mn.sysbusinesscode.entity.param.SysBusinessCodeParam;
import com.mn.sysbusinesscode.entity.po.SysBusinessCode;
import com.mn.sysbusinesscode.service.SysBusinessCodeService;
import com.mn.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:     SysBusinessCodeController.java
 * @Description:   业务流水号的Controller层
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-26 23:52:45
 */
@RestController
@RequestMapping("/api/sysBusinessCode")
public class SysBusinessCodeController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SysBusinessCodeController.class);
	
	@Autowired
	private SysBusinessCodeService sysBusinessCodeService;

	/**
	 * @description 查询业务流水号列表-分页
	 * @author qlz
     * @param param 分页查询参数
	 * @return 业务流水号列表
	 */
	@PostMapping(value = "/listPage")
	public Message sysBusinessCodeList(@RequestBody PageQuerier<SysBusinessCodeParam> param) {
	
		if(param == null){
			param = new PageQuerier<>();
		}
        return PageUtil.easyUIPageSuccessMsg(sysBusinessCodeService.listPage(param));
	}

	/**
	 * @description 查询业务流水号列表-不分页
	 * @author qlz
     * @param param 查询参数
	 * @return 业务流水号列表
	 */
	@PostMapping(value = "/list")
	public Message sysBusinessCodeList(@RequestBody SysBusinessCodeParam param) {
		if(param == null){
			param = new SysBusinessCodeParam();
		}
        return MessageUtil.successMsg(sysBusinessCodeService.list(param));
	}
	
	/**
	* @description 保存(新增/修改)[业务流水号]
	* @author qlz
    * @param param 保存参数
	* @return 响应对象
	*/
	@PostMapping(value = "/save")
	public Message saveSysBusinessCode(@RequestBody SysBusinessCodeParam param) {
		if(param!=null &&(param.getId()==null || "".equals(param.getId()))){
			sysBusinessCodeService.insert(param);
        }else{
			sysBusinessCodeService.update(param);
        }
        return MessageUtil.successMsg(param.getId());
	}
		
	
	/**
	* @description 批量删除业务流水号
	* @author qlz
	* @param ids 参数:主键集合
	* @return 响应对象
	*/
	@PostMapping(value = "/delete")
	public Message deleteSysBusinessCode(@RequestBody List<Long> ids) {
		sysBusinessCodeService.delete(ids);
		return MessageUtil.successMsg();
	}
	
	
	 /**
	* @description 获取单个业务流水号
	* @author qlz
	* @param id 参数:主键
	* @return 响应对象
	*/
	@GetMapping(value = "/get")
	public Message getSysBusinessCode(Long id) {
		SysBusinessCode sysBusinessCode = sysBusinessCodeService.get(id);
		return MessageUtil.successMsg(sysBusinessCode);
	}
}