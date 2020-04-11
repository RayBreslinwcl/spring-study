package com.ray.mapper;

import com.ray.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

/**
 * Created by Administrator on 2020/4/11.
 */
public class UserMapperImp implements UserMapper {
    //之前都是使用sqlSession执行
    //之后都是使用sqlSessionTemplate
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> select() {
//        return null;
        return sqlSession.getMapper(UserMapper.class).select();
    }
}
