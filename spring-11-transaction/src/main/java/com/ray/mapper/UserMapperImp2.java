package com.ray.mapper;

import com.ray.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by Administrator on 2020/4/11.
 */
public class UserMapperImp2 extends SqlSessionDaoSupport implements UserMapper {
    public List<User> select() {
//        SqlSession sqlSession=getSqlSession();
//        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
//        return userMapper.select();

        //为了测试事务，直接添加
        User user=new User(10,"上","12453234");
        UserMapper userMapper=getSqlSession().getMapper(UserMapper.class);
        userMapper.addUser(user);
        userMapper.deleteUser(6);

//        精简一行
        return getSqlSession().getMapper(UserMapper.class).select();


    }


    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);

    }
}
