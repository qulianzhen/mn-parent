package com.mn.module.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 缓存配置
 * @Author:Mloong
 * @Date :create in 2020/8/31-0:29
 * @Version 1.0
 * @Modified By:
 */
@Component
@ConfigurationProperties(prefix = "mncache")
public class CacheProperties {
    private final Map<String, Duration> initCaches = new HashMap<>();

    public Map<String, Duration> getInitCaches() {
        return initCaches;
    }
}
