package com.ray.demo03;

import com.sun.org.apache.regexp.internal.RE;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2020/4/10.
 */
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    //生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),rent.getClass().getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        return null;

        //之前
        seeHouse();

        //执行代理对象的方法
        //动态代理本质，就是使用反射实现
        Object result= method.invoke(rent,args);

        //之后签合同
        hetong();
        return result;
    }

    //=====================================================
    //中介代理过程中添加的骚操作
    public void seeHouse(){
        System.out.println("中介带你看房");
    }

    public void hetong(){
        System.out.println("签订合同");
    }
}
