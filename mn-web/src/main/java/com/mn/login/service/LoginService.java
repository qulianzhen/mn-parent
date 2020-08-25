package com.mn.login.service;

import com.mn.login.entity.vo.LoginSuccessInfoVo;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2019/3/1-3:04
 * @Version 1.0
 * @Modified By:
 */
public interface LoginService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录信息
     */
    LoginSuccessInfoVo login(String username, String password);
}
