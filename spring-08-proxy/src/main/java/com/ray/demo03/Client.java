package com.ray.demo03;

/**
 * Created by Administrator on 2020/4/10.
 */
public class Client {
    public static void main(String[] args) {

        //真实角色
        Host host=new Host();

        //代理角色
        //通过调用程序处理角色，来处理我们要调用的接口对象
        ProxyInvocationHandler pih=new ProxyInvocationHandler();
        pih.setRent(host);

        //动态生成
        Rent proxy=(Rent)pih.getProxy();
        //调用方法
        proxy.rent();

    }
}
