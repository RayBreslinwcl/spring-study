package com.ray.mapper;

import com.ray.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2020/4/11.
 */
public interface UserMapper {
    //查询所有用户
    public List<User> select();

}
