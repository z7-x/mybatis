package com.example.mybatis.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.Test;
import org.springframework.stereotype.Component;

/**
 * @Classname AspectConfig
 * @Description TODO
 * @Date 2021/3/8 2:49 下午
 * @Author z7-x
 */
@Aspect
@Component
@Slf4j
public class AspectConfig {

    /**
     * 定义一个切入点.
     * 解释下：
     * <p>
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在controller包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut(value = "execution(public * com.example..*.controller..*.*(..))")
    public void log() {
        log.info("读取aop....");
    }

    /**
     * @param joinPoint 可以通过他来获取方法以及参数
     * @Before("log()") 前置通知，在切入点之前
     */
    @Before("log()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("前置通知...参数为：" + arg);

        }
    }

    /**
     * @param joinPoint
     * @param returnValue
     * @AfterReturning(value = "log()",returning = "returnValue")
     * returning 获取返回值
     */
    @AfterReturning(value = "log()", returning = "returnValue")
    public void returning(JoinPoint joinPoint, Object returnValue) {
        System.out.println("返回通知...结果为：" + returnValue);
    }

}
