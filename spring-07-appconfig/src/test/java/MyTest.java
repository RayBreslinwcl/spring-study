import com.ray.config.myconfig;
import com.ray.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Administrator on 2020/4/10.
 */
public class MyTest {

    public static void main(String[] args) {

        //配置类，实现获取环境变量
        ApplicationContext context = new AnnotationConfigApplicationContext(myconfig.class);

        User getUser = context.getBean("getUser", User.class);
        System.out.println(getUser);
    }
}
