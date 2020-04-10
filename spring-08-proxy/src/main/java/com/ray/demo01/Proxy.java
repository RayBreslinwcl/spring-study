package com.ray.demo01;

/**
 * Created by Administrator on 2020/4/10.
 * 代理
 */
public class Proxy implements Rent {
    //代理对象
    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    public void rent() {
        seeHouse();
        host.rent();
        hetong();
    }


    //中介代理过程中添加的骚操作
    public void seeHouse(){
        System.out.println("中介带你看房");
    }

    public void hetong(){
        System.out.println("签订合同");
    }

}
