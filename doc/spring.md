## 二、spring-hello（spring-02）

### 0.官方参考文档

https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/core.html#spring-core

### 1.步骤

1. 导入pom(父依赖有，所以不用导入)

   ```java
           <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-webmvc</artifactId>
               <version>5.2.0.RELEASE</version>
           </dependency>
   ```

   

2. 写pojo类

   ```java
   package com.ray.pojo;
   
   /**
    * Created by Administrator on 2020/4/7.
    */
   public class Hello {
       private String str;
   
       public String getStr() {
           return str;
       }
   
       public void setStr(String str) {
           this.str = str;
       }
   
       @Override
       public String toString() {
           return "Hello{" +
                   "str='" + str + '\'' +
                   '}';
       }
   }
   
   ```

   

3. 写beans.xml文件

参考官方文档：https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/core.html#spring-core

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="..." class="...">  
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions go here -->

</beans>
```

修改为beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--使用spring来创建对象，Spring来管理这些bean-->
    <!--spring创建对象过程
    类型 变量名 = new 类型（）
    Hello hello=new Hello（）；

    id=变量名
    class=new的对象
    property 相当于给对象的属性赋值-->
    <bean id="hello" class="com.ray.pojo.Hello">
        <property name="str" value="hello spring!"/>
    </bean>

</beans>
```

4. 测试

```java
import com.ray.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2020/4/7.
 */
public class MyTest {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello);
    }
}

```

### 2.总结IOC

（1）hello对象式由Spring创建

（2）hello对象的属性，是由Spring容器设置的

（3）反转：程序本身不创建对象，而变成被动的接收对象.

（4）依赖注入：就是利用set方法来进行注入的.



### 3.ClassPathXmlApplicationContext源码



## 三、IOC创建对象方式（spring-03-ioc2）

### 1.创建测试环境



### 2.需要无参构造函数(默认是无参构造函数)

```java
package com.ray.pojo;

/**
 * Created by Administrator on 2020/4/7.
 */
public class Hello {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "str='" + str + '\'' +
                '}';
    }
}

```

### 3.有参构造

1. 代码

```java
package com.ray.pojo;

/**
 * Created by Administrator on 2020/4/7.
 */
public class User {
    String name;

    public User() {
        System.out.println("这个是无参构造方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println(name);
    }
}

```

2. xml配置文件(下标赋值)

```

```



## 四、spring配置

### 1.别名（bias和bean里的name）

```xml
    <!--别名，别名和原名都可以-->
    <alias name="user" alias="userbias"/>
```



### 2.bean配置

```xml
    <!--name也是别名，可以取多个别名，建议使用-->
    <bean id="userT" class="com.ray.pojo.User2" name="user2222,user2_2,user44 user53"/>

```



### 3.import：可以多个配置文件合并为一个，一般团队开发使用

假设，现在项目中多个开发，此时都注入到applicationContext.xml，而后直接使用总配置即可

applicationContext.xml如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

   <import resource="beans.xml"/>
   <import resource="beans2.xml"/>


</beans>
```



## 五、依赖注入

官网：https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-dependencies

### 1.构造器注入（在三中讲解）

### 2.set注入（重点核心！）【spring-04-di】

【官网：https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-dependencies】

依赖注入：set注入

​	依赖：bean对象创建，依赖于容器

​	注入：bean对象中所有属性，由容器来注入



1.  构造测试环境【spring-04-di】

   

2. 普通、set、map等

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <bean id="address" class="com.ray.pojo.Address"/>
   
       <bean id="student" class="com.ray.pojo.Student">
           <!--第1种，普通注入-->
           <property name="name" value="ray 666"/>
   
           <!--第2种，bean注入，ref-->
           <property name="address" ref="address"/>
   
           <!--第3种，数组注入-->
           <property name="books">
               <array>
                   <value>红楼梦</value>
                   <value>西游记</value>
                   <value>三国</value>
               </array>
           </property>
   
           <property name="hobbys">
               <list>
                   <value>听歌</value>
                   <value>看电影</value>
               </list>
           </property>
   
           <property name="card">
               <map>
                   <entry key="身份证" value="123123434"/>
                   <entry key="身份证2" value="123123434"/>
               </map>
           </property>
   
           <property name="games">
               <set>
                   <value>LOL</value>
                   <value>COC</value>
               </set>
           </property>
   
           <!--null注入-->
           <property name="wife">
   
               <!--<null></null>-->
               <null/>
           </property>
           
           <!--Properties-->
           <property name="info">
               <props>
                   <prop key="学号">23</prop>
                   <prop key="学号2">2343</prop>
                   <prop key="性别">男</prop>
               </props>
           </property>
   
       </bean>
   </beans>
   ```

   

### 3.其他方式





## 六、Bean作用域【spring-04-di】

[参考：https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-factory-scopes]

| Scope                                                        | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [singleton](https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-singleton) | (Default) Scopes a single bean definition to a single object instance for each Spring IoC container. |
| [prototype](https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-prototype) | Scopes a single bean definition to any number of object instances. |
| [request](https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-request) | Scopes a single bean definition to the lifecycle of a single HTTP request. That is, each HTTP request has its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware Spring `ApplicationContext`. |
| [session](https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-session) | Scopes a single bean definition to the lifecycle of an HTTP `Session`. Only valid in the context of a web-aware Spring `ApplicationContext`. |
| [application](https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-factory-scopes-application) | Scopes a single bean definition to the lifecycle of a `ServletContext`. Only valid in the context of a web-aware Spring `ApplicationContext`. |
| [websocket](https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/web.html#websocket-stomp-websocket-scope) | Scopes a single bean definition to the lifecycle of a `WebSocket`. Only valid in the context of a web-aware Spring `ApplicationContext`. |

1. singleton单例

```xml
    <bean id="address" class="com.ray.pojo.Address" scope="singleton">
        <property name="address" value="address 地址"/>
    </bean>
```



2. prototype原型模式

   每次从容器种get，都会产生一个新对象

```xml
    <bean id="address" class="com.ray.pojo.Address" scope="prototype">
        <property name="address" value="address 地址"/>
    </bean>
```



