package com.mn.dict.entity.po;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2019/2/17-23:27
 * @Version 1.0
 * @Modified By:
 */
//@ConfigurationProperties 告诉springBoot本类中的所有属性和配置文件中的配置相绑定
//只有这个Bean是容器中的组件，才能使用容器提供的@ConfigurationProperties功能
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String,Object> mapObj;
    private List<Object> lists;
    private Dog dog;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMapObj() {
        return mapObj;
    }

    public void setMapObj(Map<String, Object> mapObj) {
        this.mapObj = mapObj;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", mapObj=" + mapObj +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
