package com.ray.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;

//import javax.annotation.Resource;
/**
 * Created by Administrator on 2020/4/8.
 */
public class People {
//    @Autowired
    @javax.annotation.Resource
    Cat cat;
//    @Autowired
//    @Qualifier(value = "dog22")
    @javax.annotation.Resource(name = "dog22")
    Dog dog;
    String name;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}
