package com.mn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @Description: 系统配置Bean
 * @Author:Mloong
 * @Date :create in 2019/3/10-2:34
 * @Version 1.0
 * @Modified By:
 */
@Configuration
//这个注解，其实只支持properties文件，不支持yml，如果想支持yml，需要
//自定义一个工厂类 ResourceFactory，继承这个 default 工厂，重写 createPropertySource
@PropertySource(value = "classpath:mnSystemConfig.yml",factory = ResourceFactory.class)
public class MnSystemConfig {


    public static String version;

    public static int verificationCodeStyle;

    public static List<String> accessAnonList;

    public static List<String> accessAuthcList;

    public static List<String> accessPermsList;

    @Value("${mn.system.version}")
    public static void setVersion(String version) {
        MnSystemConfig.version = version;
    }

    @Value("${login.verificationCodeStyle}")
    public static void setVerificationCodeStyle(int verificationCodeStyle) {
        MnSystemConfig.verificationCodeStyle = verificationCodeStyle;
    }

    @Value("${access.anon}")
    public static void setAccessAnonList(List<String> accessAnonList) {
        MnSystemConfig.accessAnonList = accessAnonList;
    }

    @Value("${access.authc}")
    public static void setAccessAuthcList(List<String> accessAuthcList) {
        MnSystemConfig.accessAuthcList = accessAuthcList;
    }

    @Value("${access.perms}")
    public static void setAccessPermsList(List<String> accessPermsList) {
        MnSystemConfig.accessPermsList = accessPermsList;
    }
}
