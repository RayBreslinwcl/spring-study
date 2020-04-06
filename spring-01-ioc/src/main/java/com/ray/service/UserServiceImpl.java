package com.ray.service;

import com.ray.dao.UserDao;
import com.ray.dao.UserDaoImp;

/**
 * Created by Administrator on 2020/4/6.
 */
public class UserServiceImpl  implements UserService{

//    private UserDao userDao=new UserDaoImp();
    private UserDao userDao;

    //利用set进行动态实现注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
}
