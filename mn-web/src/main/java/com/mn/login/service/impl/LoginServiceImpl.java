package com.mn.login.service.impl;

import com.mn.commonbean.exception.BusinessIncorrectException;
import com.mn.commonbean.exception.BusinessInvalidParamException;
import com.mn.login.service.LoginService;
import com.mn.mnutil.JwtUtil;
import com.mn.mnutil.StringUtil;
import com.mn.permission.entity.po.SysUser;
import com.mn.permission.service.SysUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户登录
 * @Author:Mloong
 * @Date :create in 2019/3/1-3:05
 * @Version 1.0
 * @Modified By:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public String jwtLogin(String username, String password) {
        if (StringUtil.isBlank(username) || StringUtil.isBlank(password)){
            throw new BusinessInvalidParamException("用户名或密码不能为空!");
        }

        SysUser sysUser = sysUserService.findSysUserByUserName(username);

        SimpleHash hash = new SimpleHash("md5", password, username, 2);
        String encodePassword = hash.toHex();

        if(!sysUser.getPassword().equals(encodePassword)){
            throw new BusinessIncorrectException("用户名或者密码错误!");
        }

        return JwtUtil.sign(username,encodePassword);
    }
}
