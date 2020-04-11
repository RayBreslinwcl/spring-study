import com.ray.mapper.UserMapper;
import com.ray.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2020/4/11.
 */
public class MyTest {

    @Test
    public void select() throws IOException {
//        String resources="mybatis-config.xml";
//        InputStream is = Resources.getResourceAsStream(resources);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
//
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);
//
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        List<User> users = mapper.select();
//
//        for (User user : users) {
//            System.out.println(user);
//        }

//        测试spring集成
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-config.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        for (User user : userMapper.select()) {
            System.out.println(user);
        }

    }
}
