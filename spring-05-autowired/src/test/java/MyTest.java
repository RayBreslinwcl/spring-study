import com.ray.pojo.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2020/4/8.
 */
public class MyTest {
    public static void main(String[] args) {


        ApplicationContext context= new ClassPathXmlApplicationContext("beans.xml");
        People people = (People) context.getBean("people");
        people.getCat().jiao();
        people.getDog().jiao();
        System.out.println(people);

    }
}
