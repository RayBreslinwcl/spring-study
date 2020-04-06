import com.ray.service.UserService;
import com.ray.service.UserServiceImpl;

/**
 * Created by Administrator on 2020/4/6.
 */
public class MyTest {

    public static void main(String[] args) {
        //用户调用的式业务层service，不会涉及dao层
        UserService userService=new UserServiceImpl();
        userService.getUser();
    }
}
