package com.mn.module.redis;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 状态枚举
 * @Author:Mloong
 * @Date :create in 2020/6/23-1:20
 * @Version 1.0
 * @Modified By:
 */
public class Status {
    /**
     * 过期时间相关枚举
     */
    public static enum ExpireEnum{
        //未读消息的有效期为30天
        UNREAD_MSG(30L, TimeUnit.DAYS)
        ;

        /**
         * 过期时间
         */
        private Long time;
        /**
         * 时间单位
         */
        private TimeUnit timeUnit;

        ExpireEnum(Long time, TimeUnit timeUnit) {
            this.time = time;
            this.timeUnit = timeUnit;
        }

        public Long getTime() {
            return time;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
    }
}
