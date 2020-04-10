package com.ray.logAspect;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2020/4/11.
 */
public class beforelog implements MethodBeforeAdvice {
    /**
     *
     * @param method 要执行的目标对象方法
     * @param args 参数
     * @param target 目标对象
     * @throws Throwable
     */
    public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
        System.out.println(target.getClass().getName()+"的"+method.getName()+"被执行了！");
    }
}
