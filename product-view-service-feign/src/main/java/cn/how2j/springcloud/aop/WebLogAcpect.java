package cn.how2j.springcloud.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 日志切面类
 * @Author: fk
 * @Date: 2020/4/8 15:27
 */
@Component
@Aspect
@Slf4j
public class WebLogAcpect {

    @Pointcut("execution(* cn.how2j.springcloud.web.*.*(..))")
    public void webLog(){
        System.out.println("这是pointcut");
    }

    @Before("webLog()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("这是方法执行前before");
    }

    @After("webLog()")
    public void after(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("这是方法执行后after");
    }

    @Around("webLog()")
    public void around(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("这是方法执行环绕around");
    }

}
