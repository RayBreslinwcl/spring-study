package com.ray.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2020/4/9.
 */
//等价于<bean id="user"class="com.ray.pojo.User"/>
@Component
@Scope("prototype")
public class User {
//    public String name="ray hello";

    ////相当于<property name="name"value="ray hello2"/>
    @Value("ray hello2")
    public String name="ray hello";
}
