package com.mn.login.controller;

import com.mn.commonbean.restful.Message;
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
        //这个地方的的登录没有使用
        // UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        // subject.login(token);
        // 所以，不会走Realm里面定义的认证方法，jwt控制这一块，主要是控制了登录之后，token校验的这一块
        // 在shiro的过滤器中也已经把/login 加入了不处理的路径范围中，但是，在shiro里面配置了自己的自定义jwt过滤器
        // 在过滤器里面定义了 认证  和  鉴权 的方法，如executeLogin
        String token = loginService.jwtLogin(userName,password);
        return MessageUtil.successMsg().data(token);
    }

}
