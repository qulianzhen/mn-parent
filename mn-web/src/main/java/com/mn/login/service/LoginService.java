package com.mn.login.service;

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
     * @return token
     */
    String jwtLogin(String username, String password);
}
