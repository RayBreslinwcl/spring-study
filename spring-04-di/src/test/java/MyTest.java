import com.ray.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2020/4/7.
 */
public class MyTest {
    public static void main(String[] args) {

        ApplicationContext context= new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        Student student2 = (Student) context.getBean("student");
        System.out.println(student.getName());
        System.out.println(student);

        System.out.println(student==student2);
    }
}
