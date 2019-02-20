package com.mn;

import com.mn.test.entity.po.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * @Description: 测试给Bean通过配置文件绑定值;
 *                  可以在测试期间，注入
 * @Author:Mloong
 * @Date :create in 2019/2/18-0:33
 * @Version 1.0
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBeanVal {

    @Autowired
    private Person person;

    @Test
    public void test01(){
        System.out.println(person);
    }

    @Test
    public void test02(){
        Map<String,Object> map = new HashMap<>();
        map.put(null,"sfds");
        map.put("ff","2233");

        Set<String> ss = new HashSet<String>();
        ss.add(null);
        ss.add("fdsf");
        System.out.println(map);
        System.out.println("=========");
        System.out.println(ss);
    }
}
