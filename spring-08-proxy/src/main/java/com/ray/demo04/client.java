package com.ray.demo04;

/**
 * Created by Administrator on 2020/4/10.
 */
public class client {
    public static void main(String[] args) {

        //真实角色
        Host host=new Host();
//        可以代理
        ProxyInvocationHandler pih=new ProxyInvocationHandler();
        pih.setTarget(host);
        //获取代理对象
        Rent proxy = (Rent) pih.getProxy();

//        动态生成代理类
        proxy.rent();

    }
}
