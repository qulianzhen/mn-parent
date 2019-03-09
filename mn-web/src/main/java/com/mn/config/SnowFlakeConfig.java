package com.mn.config;

import com.mn.mnutil.SnowFlake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 雪花算法的配置Bean
 * @Author:Mloong
 * @Date :create in 2019/3/10-2:34
 * @Version 1.0
 * @Modified By:
 */
@Configuration
public class SnowFlakeConfig {

    /**
     * 数据中心ID
     */
    @Value("${snowflake.datacenterId}")
    private long datacenterId;
    /**
     * 机器ID
     */
    @Value("${snowflake.machineId}")
    private long machineId;

    @Bean
    public SnowFlake snowFlake(){
        return new SnowFlake(datacenterId, machineId);
    }

}
