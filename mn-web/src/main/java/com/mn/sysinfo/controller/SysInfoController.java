package com.mn.sysinfo.controller;

import com.mn.commonbean.restful.Message;
import com.mn.mnutil.MessageUtil;
import com.mn.sysinfo.service.SysInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/10/9-21:21
 * @Version 1.0
 * @Modified By:
 */
@RestController
@RequestMapping("/api/sysInfo")
public class SysInfoController {

    @Autowired
    private SysInfoService sysInfoService;

    @GetMapping(value = "/get")
    public Message getSysInfo(){
        return MessageUtil.successMsg(sysInfoService.get());
    }

}
