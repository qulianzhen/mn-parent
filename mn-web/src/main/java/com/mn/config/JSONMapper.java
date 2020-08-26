package com.mn.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @Description: 类型转换：主要解决问题 将controller 返回的对象中long 类型转换为 string 类型返回给
 *           前端，这样前端能看到 long 保存的真实的 数据
 * @Author:Mloong
 * @Date :create in 2020/8/14-21:38
 * @Version 1.0
 * @Modified By:
 */
public class JSONMapper extends ObjectMapper {
    /**
     * @desc: 配置类型转换器
     * @auth: zhangsy
     * @data: 2019/9/20 10:09
     * @return:
     */
    public JSONMapper() {
        super();

        //日期格式转换
        //this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        this.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        this.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(long.class, ToStringSerializer.instance);
        registerModule(simpleModule);
    }

}
