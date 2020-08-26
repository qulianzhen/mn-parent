package com.mn.module.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description: Redis配置类，包含：声明RedisTemplate
 * @Author:Mloong
 * @Date :create in 2020/6/23-0:08
 * @Version 1.0
 * @Modified By:
 */
@Configuration
public class RedisConfig{

    /**
     * 配置自定义redisTemplate
     *
     * 配置redis的key跟value的序列化方式，默认使用的JdkSerializationRedisSerializer 这样的会导致我们通过
     * redis desktop manager显示的我们key跟value的时候显示不是正常字符。 所以我们需要手动配置一下序列化方式 新建一个config
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);

        // 值采用json序列化
        template.setValueSerializer(jacksonSeial);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        //template.setEnableTransactionSupport(true);//是否启用事务
        /*
        * ①afterPropertiesSet方法，初始化bean的时候执行，可以针对某个具体的bean进行配置。afterPropertiesSet 必须实现 InitializingBean接口
        * ②init-method方法，初始化bean的时候执行，可以针对某个具体的bean进行配置。init-method需要在applicationContext.xml配置文档中bean的定义里头写明。
        * 例如：<bean id="TestBean" class="nju.software.xkxt.util.TestBean" init-method="init"></bean>
        * 这样，当TestBean在初始化的时候会执行TestBean中定义的init方法
        * ③BeanPostProcessor，针对所有Spring上下文中所有的bean，可以在配置文档applicationContext.xml中配置一个BeanPostProcessor，
        * 然后对所有的bean进行一个初始化之前和之后的代理。BeanPostProcessor接口中有两个方法：
        * postProcessBeforeInitialization和postProcessAfterInitialization。 postProcessBeforeInitialization方法在bean初始化之前执行，
        * postProcessAfterInitialization方法在bean初始化之后执行。
        * 例子：public class PostProcessor implements BeanPostProcessor
        * @Override public Object postProcessBeforeInitialization(Object bean, String beanName)  throws BeansException
        * @Override public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
        * <bean class="nju.software.xkxt.util.PostProcessor"></bean>
        * 总结：最终顺序：postProcessBeforeInitialization   afterPropertiesSet    init-method   postProcessAfterInitialization
        * */
        template.afterPropertiesSet();

        return template;
    }
}
