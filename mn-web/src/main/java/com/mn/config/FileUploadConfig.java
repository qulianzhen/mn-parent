package com.mn.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @version 1.0
 * @desc 文件上传配置
 * @auth qulianzhen
 * @date 2021-03-07 19:14
 */

@Configuration
public class FileUploadConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize(DataSize.ofKilobytes(2048));//最大kb
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(5));//最大M
        return factory.createMultipartConfig();
    }
}
