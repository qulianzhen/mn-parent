package com.mn.config;

import com.mn.commonbean.restful.Message;
import com.mn.mnutil.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @desc 描述  刷新token，实现类似session无操作则30min失效的效果
 * @auth qulianzhen
 * @date 2020-04-20 23:41
 */
@Aspect
@Component
//@Lazy(false)  表示对象会在初始化的时候创建,不延迟；
public class RefreshTokenAOP {

    /**
     * 定义切入点：对要拦截的方法进行定义与限制，如包、类
     *
     * 1、execution(public * *(..)) 任意的公共方法
     * 2、execution（* set*（..）） 以set开头的所有的方法
     * 3、execution（* com.lingyejun.annotation.LoggerApply.*（..））com.lingyejun.annotation.LoggerApply这个类里的所有的方法
     * 4、execution（* com.lingyejun.annotation.*.*（..））com.lingyejun.annotation包下的所有的类的所有的方法
     * 5、execution（* com.lingyejun.annotation..*.*（..））com.lingyejun.annotation包及子包下所有的类的所有的方法
     * 6、execution(* com.lingyejun.annotation..*.*(String,?,Long)) com.lingyejun.annotation包及子包下所有的类的有三个参数，第一个参数为String类型，第二个参数为任意类型，第三个参数为Long类型的方法
     * 7、execution(@annotation(com.lingyejun.annotation.Lingyejun))
     */
    @Pointcut("execution(public com.mn.commonbean.restful.Message com.mn.*.controller.*.*(..))")
    private void cutMethod() {

    }

    /**
     * 前置通知：在目标方法执行前调用
     */
    @Before("cutMethod()")
    public void begin(JoinPoint point) {
        Object[] args=point.getArgs();
        System.out.println("==@Before== : begin");
        System.out.println(args);
    }

    /**
     * 后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行
     */
    @AfterReturning(pointcut="cutMethod()",returning = "msg")
    public void afterReturning(Message msg) {
        System.out.println("==@AfterReturning== : after returning");
        System.out.println(msg);
        if(msg!=null){
            String token = (String)SecurityUtils.getSubject().getPrincipal();
            String username = JwtUtil.getUsername(token);
            //解析token，并生成新的token
            msg.setToken(JwtUtil.sign(username,TokenConfig.secret,TokenConfig.timeout));
        }
    }

    /**
     * 后置/最终通知：无论目标方法在执行过程中出现异常都会在它之后调用
     */
    @After("cutMethod()")
    public void after() {
        System.out.println("==@After==  : finally returning");
    }

    /**
     * 异常通知：目标方法抛出异常时执行
     */
    @AfterThrowing("cutMethod()")
    public void afterThrowing() {
        System.out.println("==@AfterThrowing==  : after throwing");
    }

    @Around("cutMethod()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        /*// 获取目标方法的名称
        String methodName = joinPoint.getSignature().getName();
        // 获取方法传入参数
        Object[] params = joinPoint.getArgs();*/
        joinPoint.proceed();
    }

    /**
     * 获取方法中声明的注解
     *
     * @param joinPoint
     * @return
     * @throws NoSuchMethodException
     */
    /*public Lingyejun getDeclaredAnnotation(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        Lingyejun annotation = objMethod.getDeclaredAnnotation(Lingyejun.class);
        // 返回
        return annotation;
    }*/


}
