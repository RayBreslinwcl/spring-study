package com.ray.diylog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by Administrator on 2020/4/11.
 * 注解实现
 */
@Aspect
public class AnnotationPointCut {

    @Before("execution(* com.ray.service.UserServiceImp.*(..))")
    public void beforelog(){
        System.out.println("================注解类实现：方法执行前================");
    }

    @After("execution(* com.ray.service.UserServiceImp.*(..))")
    public void afterlog(){
        System.out.println("================注解类实现：方法执行后================");
    }

    //在环绕增强中，可以给定一个参数，代表需要获取切入点的信息
    @Around("execution(* com.ray.service.UserServiceImp.*(..))")
    public void around(ProceedingJoinPoint pro) throws Throwable {
        System.out.println("环绕前");
        Object proceed=pro.proceed();
        System.out.println("环绕后");
    }

    /**
     * 执行顺序结果
     *
     *
         环绕前
         ================注解类实现：方法执行前================
         增加了一个用户
         环绕后
         ================注解类实现：方法执行后================
     */
}
