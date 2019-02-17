package com.mn;

import com.mn.dict.entity.po.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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
}
