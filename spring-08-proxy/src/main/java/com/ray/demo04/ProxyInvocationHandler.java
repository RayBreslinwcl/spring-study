package com.ray.demo04;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2020/4/10.
 * 通用代理类
 */
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
//    private Rent rent;
    private Rent target;


    public void setTarget(Rent rent) {
        this.target = rent;
    }

    //生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //日志方法
        log(method.getName());
        //之前
        seeHouse();

        //执行代理对象的方法
        //动态代理本质，就是使用反射实现
        Object result= method.invoke(target,args);

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


    //打印日志方法
    public void log(String msg){
        System.out.println("执行了"+msg+"方法");
    }
}
