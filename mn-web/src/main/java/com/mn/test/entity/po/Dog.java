package com.mn.test.entity.po;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2019/2/17-23:29
 * @Version 1.0
 * @Modified By:
 */
public class Dog {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
