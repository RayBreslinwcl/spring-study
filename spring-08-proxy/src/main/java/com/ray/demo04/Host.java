package com.ray.demo04;

//import com.ray.demo03.Rent;

/**
 * Created by Administrator on 2020/4/10.
 * 房东
 */
public class Host implements Rent {
    public void rent(){
        System.out.println("我是房东，要租房子");
    }
}
