package com.mn.login.controller;

import com.mn.commonbean.restful.Message;
import com.mn.login.entity.vo.LoginSuccessInfoVo;
import com.mn.login.service.LoginService;
import com.mn.mnutil.MessageUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 登录Controller
 * @Author:Mloong
 * @Date :create in 2019/3/1-2:45
 * @Version 1.0
 * @Modified By:
 */
@RestController
@RequestMapping("/admin")
@Api("用户登录")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public Message login(String userName,String password){
        LoginSuccessInfoVo loginSuccessInfoVo = loginService.login(userName,password);
        return MessageUtil.successMsg().data(loginSuccessInfoVo);
    }

}
