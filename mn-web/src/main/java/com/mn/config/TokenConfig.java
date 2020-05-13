package com.mn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: jwt token的一些配置信息
 * @Author:Mloong
 * @Date :create in 2020/4/20-23:03
 * @Version 1.0
 * @Modified By:
 */
@Configuration
public class TokenConfig {

    /**
     * 名称
     */
    public static String tokenName;
    /**
     * 超时时间-分钟
     */
    public static long timeout;

    /**
     * 是否自动刷新
     */
    public static boolean refresh;
    /**
     * 加密秘钥(需要加密时才使用,如jwt时)
     */
    public static String secret;
    /**
     * 用户标志token
     */
    public static String userflg;

    @Value("${token.tokenName}")
    public void setTokenName(String tokenName){
        TokenConfig.tokenName = tokenName;
    }

    @Value("${token.timeout}")
    public void setTimeout(long timeout){
        TokenConfig.timeout = timeout;
    }


    @Value("${token.refresh}")
    public void setRefresh(boolean refresh){
        TokenConfig.refresh = refresh;
    }
    @Value("${token.secret}")
    public void setSecret(String secret){
        TokenConfig.secret = secret;
    }
    @Value("${token.userflg}")
    public void setUserflg(String userflg){
        TokenConfig.userflg = userflg;
    }

}
