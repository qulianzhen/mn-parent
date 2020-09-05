package com.mn.login.service.impl;

import com.mn.commonbean.exception.BusinessIncorrectException;
import com.mn.commonbean.exception.BusinessInvalidParamException;
import com.mn.config.TokenConfig;
import com.mn.login.entity.vo.LoginSuccessInfoVo;
import com.mn.login.service.LoginService;
import com.mn.mnutil.StringUtil;
import com.mn.module.authentication.AuthenConstants;
import com.mn.module.redis.RedisUtil;
import com.mn.sysuser.entity.po.SysUser;
import com.mn.sysuser.service.SysUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public LoginSuccessInfoVo login(String username, String password) {
        if (StringUtil.isBlank(username) || StringUtil.isBlank(password)){
            throw new BusinessInvalidParamException("用户名或密码不能为空!");
        }

        SysUser sysUser = sysUserService.findSysUserByUserName(username);

        SimpleHash hash = new SimpleHash("md5", password, username, 2);
        String encodePassword = hash.toHex();

        if(sysUser==null || !sysUser.getPassword().equals(encodePassword)){
            throw new BusinessIncorrectException("用户名或者密码错误!");
        }
        LoginSuccessInfoVo loginSuccessInfoVo = new LoginSuccessInfoVo();
        loginSuccessInfoVo.setSubject(username);
        loginSuccessInfoVo.setNickName(sysUser.getNickName());
        loginSuccessInfoVo.setToken(UUID.randomUUID().toString().replace("-",""));
        //这里如果之前已经存在该key，则会重置value，即用户在电脑A登录，又在客户端B登录，此时，token值变化了，A端会被"挤掉"
        redisUtil.set(AuthenConstants.USERTOKENPREFIX + username,loginSuccessInfoVo.getToken(),TokenConfig.timeout*60);
        return loginSuccessInfoVo;
    }

    @Override
    public void logout(String userName) {
        SysUser sysUser = sysUserService.get(userName);
        redisUtil.del(AuthenConstants.USERTOKENPREFIX + userName);
        redisUtil.del("MN_USER_PERMISSION::" + sysUser.getId());
    }

    public static void main(String[] args) {
        SimpleHash hash = new SimpleHash("md5", "1", "qlz", 2);
        String encodePassword = hash.toHex();
        System.out.println("args = [" + encodePassword + "]");
        System.out.printf(UUID.randomUUID().toString());
    }
}
