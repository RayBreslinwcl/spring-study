package com.ray.config;

import com.ray.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2020/4/10.
 */
@Configuration //代表这个是一个配置类，和beans.xml一样
@ComponentScan("com.ray.pojo")
public class myconfig2 {

    /**
     * 获取用户
     * @return
     */
    //注册一个bean
    //方法名字，就相当于bean的id
    //返回值，就相当于bean标签中的class属性
    @Bean
    public User getUser(){
        return new User();
    }
}
