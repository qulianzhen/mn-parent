package com.mn.system.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义一个对象用来包装token
 */
public class JwtToken implements AuthenticationToken{
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
