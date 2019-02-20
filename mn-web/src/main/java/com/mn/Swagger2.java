package com.mn;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.show}")
//http://localhost:8080/mn-web/swagger-ui.html
public class Swagger2 {

    /**
     * 可以定义多个组
     * @return
     */
    @Bean
    public Docket dictApi() {
        //这是一个过滤器，可以控制什么样的方法才被显示（个人认为可以通过ApiOperationIgnore那个注解来解决）
        /*Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                if (input.isAnnotatedWith(ApiOperation.class)){
                    //只有添加了ApiOperation注解的method才在API中显示
                        return true;}
                    return false;
            } };*/
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("字典模块")
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mn.dict.controller"))
                .paths(PathSelectors.any())//过滤的接口
                .build();
    }
    /**
     * 可以定义多个组
     * @return
     */
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试模块")
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mn.test.controller"))
                .paths(PathSelectors.any()).build();
    }
    /**
     * 可以定义多个组
     * @return
     */
    @Bean
    public Docket menuApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("菜单模块")
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mn.menu.controller"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * 可以定义多个
     * @return
     */
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("月神系统")
                .description("内部API")
                .version("1.0")
                .license("Apache 2.0")
                .termsOfServiceUrl("NO terms of service")
                .contact(new Contact("Q", "https://www.baidu.com", "1094590717@qq.com"))//作者
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
