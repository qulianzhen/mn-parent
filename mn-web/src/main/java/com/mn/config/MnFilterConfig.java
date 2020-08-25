package com.mn.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;

/**
 * @version 1.0
 * @desc 描述   Filter的配置中心
 * @auth qulianzhen
 * @date 2020-04-22 21:00
 */
@Configuration
public class MnFilterConfig {

    @Bean(name="mnAccessFilter")
    public MnAccessFilter sysVisitFilter(){
        return new MnAccessFilter();
    }
    @Bean(name="xssFilter")
    public XssFilter xssFilter(){
        return new XssFilter();
    }

    @Bean
    public FilterRegistrationBean sysVisitFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //
        registrationBean.setFilter(new DelegatingFilterProxy("mnAccessFilter"));
        registrationBean.addInitParameter("targetFilterLifecycle","true");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);//order的数值越小 则优先级越高
        //registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico");
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        // 这里这里这里?不写这个会执行两次？
        registrationBean.setEnabled(false);
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean xssFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //
        registrationBean.setFilter(new DelegatingFilterProxy("xssFilter"));
        registrationBean.addInitParameter("targetFilterLifecycle","true");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);//order的数值越小 则优先级越高
        //registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico");
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        // 这里这里这里?不写这个会执行两次？
        registrationBean.setEnabled(false);
        return registrationBean;
    }
}
