package com.mn.test.controller;

import com.mn.mnutil.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: (测试Controller)
 * @Author:Mloong
 * @Date :create in 2019/2/14-22:18
 * @Version 1.0
 * @Modified By: qlz
 */
@RestController
public class TestController {

    @Autowired
    private SnowFlake snowFlake;

    @GetMapping("/test")
    public String test(){
        return "This is a Test Controller!";
    }

    @GetMapping("/testSnowflake")
    public  String testSnowflake(){
        return snowFlake.nextId()+"";
    }
}
