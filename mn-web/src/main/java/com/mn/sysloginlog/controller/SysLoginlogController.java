package com.mn.sysloginlog.controller;

import com.mn.commonbean.restful.Message;
import com.mn.mnutil.SnowFlake;
import com.mn.module.page.PageQuerier;
import com.mn.sysloginlog.entity.param.SysLoginlogParam;
import com.mn.sysloginlog.service.SysLoginlogService;
import com.mn.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:     SysLoginlogController.java
 * @Description:   登录日志的Controller层
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-07 17:32:43
 */
@RestController
@RequestMapping("/api/sysLoginlog")
public class SysLoginlogController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SysLoginlogController.class);
	
	@Autowired
	private SysLoginlogService sysLoginlogService;
	@Autowired
    private SnowFlake snowFlake;
	
	/**
	 * @description 查询登录日志列表-分页
	 * @author qlz
     * @param param 分页查询参数
	 * @return 登录日志列表
	 */
	@PostMapping(value = "/listPage")
	public Message sysLoginlogList(@RequestBody PageQuerier<SysLoginlogParam> param) {
	
		if(param == null){
			param = new PageQuerier<>();
		}
        return PageUtil.easyUIPageSuccessMsg(sysLoginlogService.listPage(param));
	}
}