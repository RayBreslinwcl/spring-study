package com.ray.logAspect;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2020/4/11.
 */
public class afterlog implements AfterReturningAdvice {
    public void afterReturning(@Nullable Object returnValue, Method method, Object[] objects, @Nullable Object o1) throws Throwable {


        System.out.println("执行了"+method.getName()+"返回结果为"+returnValue);

    }
}
