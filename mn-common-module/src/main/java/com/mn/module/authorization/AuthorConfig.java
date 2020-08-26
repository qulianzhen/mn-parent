package com.mn.module.authorization;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/6/29-1:24
 * @Version 1.0
 * @Modified By:
 */
@Component
@ConfigurationProperties(prefix = "access")
public class AuthorConfig {

    private List<String> accessAnonList;

    private List<String> accessAuthcList;

    private List<String> accessPermsList;


    public List<String> getAccessAnonList() {
        return accessAnonList;
    }

    public void setAccessAnonList(List<String> accessAnonList) {
        this.accessAnonList = accessAnonList;
    }

    public List<String> getAccessAuthcList() {
        return accessAuthcList;
    }

    public void setAccessAuthcList(List<String> accessAuthcList) {
        this.accessAuthcList = accessAuthcList;
    }

    public List<String> getAccessPermsList() {
        return accessPermsList;
    }

    public void setAccessPermsList(List<String> accessPermsList) {
        this.accessPermsList = accessPermsList;
    }
}
