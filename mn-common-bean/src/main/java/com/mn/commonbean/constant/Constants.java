package com.mn.commonbean.constant;

/**
 * @Description: 通用常量枚举类
 * @Author:Mloong
 * @Date :create in 2020/9/4-1:11
 * @Version 1.0
 * @Modified By:
 */
public class Constants {

    /**
     * 是否: 1是   0否
     */
    static enum WhetherEnum{
        YES("是",1),NO("否",0);
        private Integer value;
        private String key;
        private WhetherEnum(String key,Integer value){
            this.key = key;
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
        public String getKey() {
            return key;
        }
    }

    /**
     * 有无: 1有   0无
     */
    static enum HaveOrNotEnum{
        HAVE("有",1),NOHAVE("无",0);
        private Integer value;
        private String key;
        private HaveOrNotEnum(String key,Integer value){
            this.key = key;
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
        public String getKey() {
            return key;
        }
    }

    /**
     * 性别: 1男   0女
     */
    static enum Gender2Enum{
        HAVE("男",1),NOHAVE("女",0);
        private Integer value;
        private String key;
        private Gender2Enum(String key,Integer value){
            this.key = key;
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
        public String getKey() {
            return key;
        }
    }

    /**
     * 性别: 1男   0女  2未知
     */
    static enum Gender3Enum{
        HAVE("男",1),NOHAVE("女",0),UNKNOWN("未知",2);
        private Integer value;
        private String key;
        private Gender3Enum(String key,Integer value){
            this.key = key;
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
        public String getKey() {
            return key;
        }
    }

    /**
     * 是否可用: 1启用   0禁用
     */
    static enum EnableEnum{
        ENABLE("启用",1),DISABLED("禁用",0);
        private Integer value;
        private String key;
        private EnableEnum(String key,Integer value){
            this.key = key;
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
        public String getKey() {
            return key;
        }
    }

}
