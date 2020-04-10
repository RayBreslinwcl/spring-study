package com.ray.diylog;

/**
 * Created by Administrator on 2020/4/11.
 */
public class logPointcut {
    public void before(){
        System.out.println("================方法执行前================");
    }

    public void after(){
        System.out.println("================方法执行后================");
    }
}
