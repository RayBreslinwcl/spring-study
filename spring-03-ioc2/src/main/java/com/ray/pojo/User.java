package com.ray.pojo;

/**
 * Created by Administrator on 2020/4/7.
 */
public class User {
    String name;

//    public User() {
//        System.out.println("这个是无参构造方法");
//    }

    public User(String str) {
        name=str;
        System.out.println("这个是有参构造方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println(name);
    }
}
