package com.mn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


/*
SpringBoot在写启动类的时候如果不使用@ComponentScan指明对象扫描范围，默认只扫描当前启动类所在的包里的对象，
因为启动类不能直接放在main/java文件夹下，必须要建一个包把它放进去，这是就需要使用@ComponentScan指明要扫描的包。
如：
mn-common-module模块的包名：com.mn.common
mn-web模块的包名：com.mn.web
这样默认就不会把common模块中标记@Component的组件装配到SpringBoot中，因为它默认只扫描com.mn.web包下的组件，
所以这时就需要在main()启动类中使用@ComponentScan注解来指明要扫描那些包，但只扫描该注解指定的包，当前mian()方法所在的包就不会被扫描了，
所以要写它的上级包“com.mn”
 */
/*由于各个模块包的命名都是com.mn开始，所以，此处扫描可以不用写，写上是为了提醒自己记住。*/
@ComponentScan("com.mn")
@SpringBootApplication
@MapperScan(value = "com.mn.**.mapper")
public class MnWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MnWebApplication.class, args);
    }

}
