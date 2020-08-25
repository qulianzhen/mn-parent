package com.mn.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Description: 解决Long类型json化之后最后两位变成0的问题
 * @Author:Mloong
 * @Date :create in 2020/7/19-17:07
 * @Version 1.0
 * @Modified By:
 */
public class JsonLongSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(Long.toString(aLong));
    }
}
