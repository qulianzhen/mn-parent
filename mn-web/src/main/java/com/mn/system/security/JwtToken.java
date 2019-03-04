package com.mn.system.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义一个对象用来包装token
 * Shiro 原生的 Token 中存在用户名和密码以及其他信息 [验证码，记住我]
 * 在 JWT 的 Token 中因为已将用户名和密码通过加密处理整合到一个加密串中，所以只需要一个 token 字段即可
 */
public class JwtToken implements AuthenticationToken{
    // 密钥
    private String token;

    public JwtToken(String token){
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
