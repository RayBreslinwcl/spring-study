import com.ray.pojo.User;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2020/4/7.
 */
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = (User) context.getBean("user");

        User user2 = (User) context.getBean("user");
        System.out.println(user==user2);//true：默认是单例

    }
}
