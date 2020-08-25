package com.mn.menu.controller;

import com.mn.commonbean.restful.Message;
import com.mn.config.TokenConfig;
import com.mn.menu.entity.param.SysMenuParam;
import com.mn.menu.entity.po.SysMenu;
import com.mn.menu.service.SysMenuService;
import com.mn.mnutil.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/sysMenu")
@Api(tags = "菜单接口模块")
public class SysMenuController {
    private Logger logger = LoggerFactory.getLogger(SysMenuController.class);
    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping(value = "/sysMenuTree")
    @ApiOperation(value = "查询菜单树")
    public Message listTree(SysMenuParam sysMenuParam, HttpServletRequest req){
        String loginName = req.getHeader(TokenConfig.userflg);
        sysMenuParam.setLoginName(loginName);
        return MessageUtil.successMsg(sysMenuService.listTree(sysMenuParam));
    }


    @PostMapping(value = "/getSysMenuInfo")
    @ApiOperation(value = "根据菜单id获取菜单信息")
    public Message getSysMenuInfo(Long id){
        return MessageUtil.successMsg(sysMenuService.getSysMenuInfo(id));
    }


    @PostMapping(value = "save")
    public Message save(@RequestBody SysMenu sysMenu){
        sysMenuService.save(sysMenu);
        return MessageUtil.successMsg();
    }
    @PostMapping(value = "deleteSysMeu")
    public Message deleteSysMeu(Long id){
        sysMenuService.deleteSysMeu(id);
        return MessageUtil.successMsg();
    }
}
