package com.mn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @version 1.0
 * @desc 描述  跨域设置： 分为简单跨域和复杂跨域，一般来说，如果在header中加入额外属性或者修改applicationContext都是复杂
 * 跨域，复杂跨域会导致请求时先执行一个预请求，type:OPTION，即会请求两次；可以通过设置maxage来设置多久进行一次预请求
 * 这里为了防止这些额外请求，直接改为ng方案；
 * @auth qulianzhen
 * @date 2020-04-19 17:07
 */

@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); //允许任何域名
        corsConfiguration.addAllowedHeader("*"); //允许任何头
        corsConfiguration.addAllowedMethod("*"); //允许任何方法
        corsConfiguration.setMaxAge(1800L);//每隔30分钟才会发起预检请求
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); //注册
        return new CorsFilter(source);
    }
}
