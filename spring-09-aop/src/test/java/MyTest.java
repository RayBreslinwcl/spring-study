import com.ray.service.UserService;
import com.ray.service.UserServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2020/4/11.
 */
public class MyTest {
    public static void main(String[] args) {

        //方式一：测试
        //方式二：测试
        //获取管理容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //默认jdk代理，是动态代理的是接口!
        UserService userService = context.getBean("userService", UserService.class);

        userService.add();



    }
}
