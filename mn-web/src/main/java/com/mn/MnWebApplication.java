package com.mn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(value = "com.mn.**.mapper")
public class MnWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MnWebApplication.class, args);
    }

}
