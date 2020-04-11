package com.ray.mapper;

import com.ray.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
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

//        精简一行
        return getSqlSession().getMapper(UserMapper.class).select();


    }
}
