package com.ray.demo01;

/**
 * Created by Administrator on 2020/4/10.
 */
public class Client {
    public static void main(String[] args) {
        //房东要租房子
        Host host=new Host();

        //代理中介
        Proxy proxy=new Proxy(host);
        //租房子：不通过房东，直接找中介
        proxy.rent();
    }
}
