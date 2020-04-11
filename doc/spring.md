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

2. xml配置文件(下标赋值)（第一种）建议！

```xml
    <bean id="user" class="com.ray.pojo.User">
        <!--<property name="name" value="hello spring!"/>-->
        <constructor-arg index="0" value="hello contructor"/>
    </bean>
```

3. 类型（第二种）不建议

```xml
    <bean id="user" class="com.ray.pojo.User">
        <!--<property name="name" value="hello spring!"/>-->
        <constructor-arg type="java.lang.String" value="hello contructor"/>
    </bean>
```



4. 参数名（第三种）
5. 

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



## 七、bean的自动装配【spring-05】

### 1.定义

Spring会在上下文种自动寻找，并自动给bean装配属性

### 2.三种装配方式

#### 1. xml显示装配

   - 第一步：类

     ```java
     package com.ray.pojo;
     
     /**
      * Created by Administrator on 2020/4/8.
      */
     public class Cat {
         public void jiao(){
             System.out.println("miaomiao~~~~");
         }
     }
     
     //=======================================================
     
     package com.ray.pojo;
     
     /**
      * Created by Administrator on 2020/4/8.
      */
     public class Dog {
         public void jiao(){
             System.out.println("wangwang~~~~");
         }
     }
     
     
     //=======================================================
     package com.ray.pojo;
     
     import org.springframework.beans.factory.annotation.Autowired;
     
     /**
      * Created by Administrator on 2020/4/8.
      */
     public class People {
         @Autowired
         Cat cat;
         @Autowired
         Dog dog;
         String name;
     
         public Cat getCat() {
             return cat;
         }
     
         public void setCat(Cat cat) {
             this.cat = cat;
         }
     
         public Dog getDog() {
             return dog;
         }
     
         public void setDog(Dog dog) {
             this.dog = dog;
         }
     
         public String getName() {
             return name;
         }
     
         public void setName(String name) {
             this.name = name;
         }
     
         @Override
         public String toString() {
             return "People{" +
                     "cat=" + cat +
                     ", dog=" + dog +
                     ", name='" + name + '\'' +
                     '}';
         }
     }
     
     ```

     

   - 第二步：xml显示配置

   ```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

    <bean id="cat" class="com.ray.pojo.Cat" scope="singleton" />
    <bean id="dog222" class="com.ray.pojo.Dog" scope="singleton" />
    <bean id="dog22" class="com.ray.pojo.Dog" scope="singleton" />
    <bean id="dog2222222" class="com.ray.pojo.Dog" scope="singleton" />

    <!--byName：会自动在容器上下文中查找，和自己对象set方法后面的值对应的beanid-->
    <!--byType：会自动在容器上下文中查找，和自己对象属性类型对应的bean-->
    <bean id="people" class="com.ray.pojo.People" scope="singleton" autowire="byType" >
        <property name="cat" ref="cat"/>
        <property name="dog" ref="dog22"/>
        <property name="name" value="kuangshne~"/>
    </bean>
    <!--<bean id="people" class="com.ray.pojo.People" scope="singleton" />-->

</beans>
   ```

   - 第三步：测试

   ```java
   public class MyTest {
       public static void main(String[] args) {
   
   
           ApplicationContext context= new ClassPathXmlApplicationContext("beans.xml");
           People people = (People) context.getBean("people");
           people.getCat().jiao();
           people.getDog().jiao();
           System.out.println(people);
   
       }
   }
   ```

   

#### 2. java中显示装配

#### 3. 隐式自动装配【重要！】

##### 3.1 修改xml，添加bean的autowire属性

- byName

- byType

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <bean id="cat" class="com.ray.pojo.Cat" scope="singleton" />
      <bean id="dog" class="com.ray.pojo.Dog" scope="singleton" />
  
      <!--byName：会自动在容器上下文中查找，和自己对象set方法后面的值对应的beanid-->
      <!--byType：会自动在容器上下文中查找，和自己对象属性类型对应的bean-->
      <bean id="people" class="com.ray.pojo.People" scope="singleton" autowire="byType" >
          <!--<property name="cat" ref="cat"/>-->
          <!--<property name="dog" ref="dog"/>-->
          <property name="name" value="kuangshne~"/>
      </bean>
  
  
  </beans>
  ```



##### 3.2 总结

byName：保证所有bean的id唯一，并且这个bean需要和注入的值一样

byType：保证所有bean的class唯一，并且这个bean需要和注入的属性一样



### 3.使用注解实现自动装配

#### 1.导入约束：context约束

#### 2.配置注解的支持beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

针对类修改beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

    <bean id="cat" class="com.ray.pojo.Cat" scope="singleton" />
    <bean id="dog" class="com.ray.pojo.Dog" scope="singleton" />

    <!--byName：会自动在容器上下文中查找，和自己对象set方法后面的值对应的beanid-->
    <!--byType：会自动在容器上下文中查找，和自己对象属性类型对应的bean-->
    <!--<bean id="people" class="com.ray.pojo.People" scope="singleton" autowire="byType" >-->
        <!--&lt;!&ndash;<property name="cat" ref="cat"/>&ndash;&gt;-->
        <!--&lt;!&ndash;<property name="dog" ref="dog"/>&ndash;&gt;-->
        <!--<property name="name" value="kuangshne~"/>-->
    <!--</bean>-->
    <bean id="people" class="com.ray.pojo.People" scope="singleton" />

</beans>
```

#### 3.类中注解@Autowired

```java
package com.ray.pojo;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2020/4/8.
 */
public class People {
    @Autowired
    Cat cat;
    @Autowired
    Dog dog;
    String name;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toautoString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}

```

【重点：】Autowired默认，如果只有一个bean实例，则是byType，通过类型匹配；如果有多个bean实例，则是byName，通过beanid来匹配。此时，需要通过@Qualifile指定beanid

可以通过@Qualifile关键字，匹配bean的id，指定唯一beanid。

```java
package com.ray.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Administrator on 2020/4/8.
 */
public class People {
    @Autowired
    Cat cat;
    @Autowired
    @Qualifier(value = "dog22")
    Dog dog;
    String name;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}

```



#### 4.测试，有注解进来

```java
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

结果：
miaomiao~~~~
wangwang~~~~
People{cat=com.ray.pojo.Cat@192d3247, dog=com.ray.pojo.Dog@3ecd23d9, name='null'}
```

#### 5.resource注解（java原生注解）

```java
package com.ray.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;

//import javax.annotation.Resource;
/**
 * Created by Administrator on 2020/4/8.
 */
public class People {
//    @Autowired
    @javax.annotation.Resource
    Cat cat;
//    @Autowired
//    @Qualifier(value = "dog22")
    @javax.annotation.Resource(name = "dog22")
    Dog dog;
    String name;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}

```

6. #### @Autowired和  @javax.annotation.Resource(name = "dog22")区别

- 相同

  都是用来自动装配的，都可以放在属性字段上

- 区别

  @Autowired先通过byType的方式实现，而且必须要求这个对象存在！如果超出两个bean，则通过byName实现【常用】
  @Resource默认通过byname的方式实现，如果找不到名字，则通过byType实现！如果两个都找不到的情况下，就报错！【常用】
  执行顺序不同：@Autowired 通过byType的方式实现。@Resource默认通过byname的方式实现







## 八、使用注解开发[spring-06]

### 1.前提

#### 1.导入aop依赖（默认导入mvc依赖后直接导入）

![1586382951999](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1586382951999.png)

#### 2.导入xml注解配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>


</beans>
```

#### 3.配置扫描包，直接

```xml
    <!--扫描pojo下的bean-->
    <context:component-scan base-package="com.ray.pojo"/>
    <context:annotation-config/>
```

#### 4.类中通过注解赋值

```java
package com.ray.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2020/4/9.
 */
//等价于<bean id="user"class="com.ray.pojo.User"/>
@Component
public class User {
//    public String name="ray hello";

    ////相当于<property name="name"value="ray hello2"/>
    @Value("ray hello2")
    public String name="ray hello";
}

```

#### 5.测试

```java
import com.ray.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2020/4/9.
 */
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user= (User) context.getBean("user");
        System.out.println(user.name);

    }
}

```



### 2.bean

### 3.属性如何注入【见1中】

### 4.@Component衍生的注解

@Component有几个衍生

- dao层：@Repository
- service:@Service
- controller:@Controller

### 5.自动装配

```
@Autowired:自动装配，通过类型-名字
	如果Autowired不唯一，则通过@Qualifier指定beanid
@Resource：自动装配，通过名字-类型
```

### 6.作用域

```java
@Component
@Scope("prototype")
public class User
```



###  7.小结

xml与注解：

- xml更加万能，适用于任何场合！维护简单方便
- 注解不是自己类使用不了，维护相对复杂！

xml与注解最佳实践：

- oxml用来管理bean；
- 注解只负责完成属性的注入；
- 我们在使用的过程中，只需要注意一个问题：必须让注解生效，就需要开启注解的支持

```xml
    <!--扫描pojo下的bean-->
    <context:component-scan base-package="com.ray.pojo"/>
    <context:annotation-config/>
```



## 九、使用JavaConfig实现配置

【参考：https://docs.spring.io/spring/docs/5.2.0.RELEASE/spring-framework-reference/core.html#beans-java-basic-concepts】

### 1.完全使用注解，而不使用beans.xml，官网上给出

```java
@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```

等效配置文件

```xml
<beans>
    <bean id="myService" class="com.acme.services.MyServiceImpl"/>
</beans>
```

### 2.实现步骤

#### 2.1 创建类

```java
package com.ray.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2020/4/10.
 */
//@Component
public class User {
    @Value("ray")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

```

#### 2.2 创建配置类myconfig

````java
package com.ray.config;

import com.ray.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2020/4/10.
 */
@Configuration //代表这个是一个配置类，和beans.xml一样
@ComponentScan("com.ray.pojo")
@Import(myconfig2.class) //引入其他配置类
public class myconfig {

    /**
     * 获取用户
     * @return
     */
    //注册一个bean
    //方法名字，就相当于bean的id
    //返回值，就相当于bean标签中的class属性
    @Bean
    public User getUser(){
        return new User();
    }
}

````

关键点

（1）@Configuration //代表这个是一个配置类，和beans.xml一样

（2） @Bean等效配置文件中的<bean>标签，注入被管理类。

（3）额外注解：

@ComponentScan("com.ray.pojo") //扫描包
@Import(myconfig2.class) //引入其他配置类

```java
package com.ray.config;

import com.ray.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2020/4/10.
 */
@Configuration //代表这个是一个配置类，和beans.xml一样
@ComponentScan("com.ray.pojo")
public class myconfig2 {

    /**
     * 获取用户
     * @return
     */
    //注册一个bean
    //方法名字，就相当于bean的id
    //返回值，就相当于bean标签中的class属性
    @Bean
    public User getUser(){
        return new User();
    }
}

```



#### 2.3 测试

```java
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

```





## 十、代理模式【spring-08-proxy】

### 10.1 简介

1. 为什么学习代理模式？因为是springaop底层。

2. 代理分类

静态代理

动态代理

3. 代理图解

![1586529469101](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1586529469101.png)

### 10.2 创建环境准备

不需要导入依赖

### 10.3静态代理

1. 角色分析：

- 抽象角色：一般会使用接口或者抽象类来解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
- 客户：访问代理对象的人！

2. 分析逻辑图

![1586529738275](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1586529738275.png)

3. 代码demo01包下

![1586530681675](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1586530681675.png)

- 抽象角色：一般会使用接口或者抽象类来解决

  ```java
  package com.ray.demo01;
  
  /**
   * Created by Administrator on 2020/4/10.
   */
  public interface Rent {
      //出租房屋
      public void rent();
  }
  
  ```

  

- 真实角色：被代理的角色

  ```java
  package com.ray.demo01;
  
  /**
   * Created by Administrator on 2020/4/10.
   * 房东
   */
  public class Host implements Rent {
      public void rent(){
          System.out.println("我是房东，要租房子");
      }
  }
  
  ```

  

- 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作

  ```java
  package com.ray.demo01;
  
  /**
   * Created by Administrator on 2020/4/10.
   * 代理
   */
  public class Proxy implements Rent {
      //代理对象
      private Host host;
  
      public Proxy() {
      }
  
      public Proxy(Host host) {
          this.host = host;
      }
  
      public void rent() {
          seeHouse();
          host.rent();
          hetong();
      }
  
  
      //中介代理过程中添加的骚操作
      public void seeHouse(){
          System.out.println("中介带你看房");
      }
  
      public void hetong(){
          System.out.println("签订合同");
      }
  
  }
  
  ```

  

- 客户：访问代理对象的人！

```java
package com.ray.demo01;

/**
 * Created by Administrator on 2020/4/10.
 */
public class Client {
    public static void main(String[] args) {
        //房东要租房子
        Host host=new Host();

        //代理中介
        Proxy proxy=new Proxy(host);
        //租房子：不通过房东，直接找中介
        proxy.rent();
    }
}

```



4. 静态代理好处

```
代理模式的好处：
·可以使真实角色的操作更加纯粹！不用去关注一些公共的业务
·公共也就就交给代理角色！实现了业务的分工！
·公共业务发生扩展的时候，方便集中管理！
```

5. 静态代理缺点

```
·一个真实角色就会产生一个代理角色；代码量会翻倍一开发效率会变低~
```





### 10.4 动态代理

#### 10.4.1 简介

- 动态代理和静态代理角色一样

- 动态代理的代理类是动态生成的，不是我们直接写好的！

- 动态代理分为两大类：基于接口的动态代理，基于类的动态代理

  	- 基于接口-jdk动态代理【测试使用】

  - 基于类：cglib



#### 10.4.2 使用两个类

Proxy和InvocationHandler





#### 10.4.3动态代理类优势

- 可以使真实角色的操作更加纯粹！不用去关注一些公共的业务
- 公共也就就交给代理角色！实现了业务的分工！
- 公共业务发生扩展的时候，方便集中管理！
- 一个动态代理类代理的是一个接口，一般就是对应的一类业务
- 一个动态代理类可以代理多个类，只要是实现了同一个接口即可！|

#### 10.4.4 实现代码demo03

- 抽象角色：一般会使用接口或者抽象类来解决

  ```java
  package com.ray.demo03;
  
  /**
   * Created by Administrator on 2020/4/10.
   */
  public interface Rent {
      //出租房屋
      public void rent();
  }
  
  ```

  

- 真实角色：被代理的角色

```java
package com.ray.demo03;

/**
 * Created by Administrator on 2020/4/10.
 * 房东
 */
public class Host implements Rent {
    public void rent(){
        System.out.println("我是房东，要租房子");
    }
}

```

- 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作。通过动态代理类创建

```java
package com.ray.demo03;

import com.sun.org.apache.regexp.internal.RE;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2020/4/10.
 */
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    //生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),rent.getClass().getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        return null;

        //之前
        seeHouse();

        //执行代理对象的方法
        //动态代理本质，就是使用反射实现
        Object result= method.invoke(rent,args);

        //之后签合同
        hetong();
        return result;
    }

    //=====================================================
    //中介代理过程中添加的骚操作
    public void seeHouse(){
        System.out.println("中介带你看房");
    }

    public void hetong(){
        System.out.println("签订合同");
    }
}

```



- 客户：访问代理对象的人！

```java
package com.ray.demo03;

/**
 * Created by Administrator on 2020/4/10.
 */
public class Client {
    public static void main(String[] args) {

        //真实角色
        Host host=new Host();

        //代理角色
        //通过调用程序处理角色，来处理我们要调用的接口对象
        ProxyInvocationHandler pih=new ProxyInvocationHandler();
        pih.setRent(host);

        //动态生成
        Rent proxy=(Rent)pih.getProxy();
        //调用方法
        proxy.rent();

    }
}

```



#### 10.4.5 实现代码demo04万能代理

- 抽象角色：一般会使用接口或者抽象类来解决

  ```java
  package com.ray.demo03;
  
  /**
   * Created by Administrator on 2020/4/10.
   */
  public interface Rent {
      //出租房屋
      public void rent();
  }
  
  ```

  

- 真实角色：被代理的角色

```java
package com.ray.demo03;

/**
 * Created by Administrator on 2020/4/10.
 * 房东
 */
public class Host implements Rent {
    public void rent(){
        System.out.println("我是房东，要租房子");
    }
}

```

- 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作。通过动态代理类创建

```java
package com.ray.demo04;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2020/4/10.
 * 通用代理类
 */
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
//    private Rent rent;
    private Rent target;


    public void setTarget(Rent rent) {
        this.target = rent;
    }

    //生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //日志方法
        log(method.getName());
        //之前
        seeHouse();

        //执行代理对象的方法
        //动态代理本质，就是使用反射实现
        Object result= method.invoke(target,args);

        //之后签合同
        hetong();
        return result;
    }

    //=====================================================
    //中介代理过程中添加的骚操作
    public void seeHouse(){
        System.out.println("中介带你看房");
    }

    public void hetong(){
        System.out.println("签订合同");
    }


    //打印日志方法
    public void log(String msg){
        System.out.println("执行了"+msg+"方法");
    }
}

```



- 客户：访问代理对象的人！

```java
package com.ray.demo04;

/**
 * Created by Administrator on 2020/4/10.
 */
public class client {
    public static void main(String[] args) {

        //真实角色
        Host host=new Host();
//        可以代理
        ProxyInvocationHandler pih=new ProxyInvocationHandler();
        pih.setTarget(host);
        //获取代理对象
        Rent proxy = (Rent) pih.getProxy();

//        动态生成代理类
        proxy.rent();

    }
}

```



## 十一、AOP实现方式一【spring-09-aop】

### 11.1 概念

AOPIAspect Oriented Programming）意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

![1586558949844](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1586558949844.png)



### 11.2 作用

提供声明式事务；允许用户自定义切面

- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志，安全，缓存，事务等等…
- 切面（ASPECT）：横切关注点被模块化的特殊对象。即，它是一个类。·通知（Advice）：切面必须要完成的工作。即，它是类中的一个方法。
- 目标（Target）：被通知对象。
- 代理（Proxy）：向目标对象应用通知之后创建的对象。
- 切入点（PointCut）：切面通知执行的“地点”的定义。
- 连接点（JointPoint）：与切入点匹配的执行点。

![1586559088899](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1586559088899.png)

### 11.3 5种advice

SpringAOP中，通过Advice定义横切逻辑，Spring中支持5种类型的Advice：

![advice](E:\Tools\WorkspaceforMyeclipse\spring-study\doc\imgs\advice-5.png)

### 11.4 spring实现aop

1. 导入依赖

   ```xml
           <dependency>
               <groupId>org.aspectj</groupId>
               <artifactId>aspectjweaver</artifactId>
               <version>1.9.4</version>
           </dependency>
   ```

   

2. 业务类

```java
//接口
package com.ray.service;

/**
 * Created by Administrator on 2020/4/11.
 */
public interface UserService {
    public void add();
    public void delete();
    public void update();
    public void select();
}


//实现类
package com.ray.service;

/**
 * Created by Administrator on 2020/4/11.
 */
public class UserServiceImp implements UserService {
    public void add() {
        System.out.println("增加了一个用户");
    }

    public void delete() {
        System.out.println("删除了一个用户");
    }

    public void update() {
        System.out.println("更新了一个用户");
    }

    public void select() {
        System.out.println("查询了一个用户");
    }
}

```

3. 实现日志log织入：方法一

   前置日志

   ```java
   package com.ray.logAspect;
   
   import org.springframework.aop.MethodBeforeAdvice;
   import org.springframework.lang.Nullable;
   
   import java.lang.reflect.Method;
   
   /**
    * Created by Administrator on 2020/4/11.
    */
   public class beforelog implements MethodBeforeAdvice {
       /**
        *
        * @param method 要执行的目标对象方法
        * @param args 参数
        * @param target 目标对象
        * @throws Throwable
        */
       public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
           System.out.println(target.getClass().getName()+"的"+method.getName()+"被执行了！");
       }
   }
   
   ```

   

   后置日志

   ```java
   package com.ray.logAspect;
   
   import org.springframework.aop.AfterAdvice;
   import org.springframework.aop.AfterReturningAdvice;
   import org.springframework.lang.Nullable;
   
   import java.lang.reflect.Method;
   
   /**
    * Created by Administrator on 2020/4/11.
    */
   public class afterlog implements AfterReturningAdvice {
       public void afterReturning(@Nullable Object returnValue, Method method, Object[] objects, @Nullable Object o1) throws Throwable {
   
   
           System.out.println("执行了"+method.getName()+"返回结果为"+returnValue);
   
       }
   }
   
   ```

   

4. applicationContext.xml导入aop约束，通入切入点

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--<context:annotation-config/>-->

    <bean id="userService" class="com.ray.service.UserServiceImp"/>
    <bean id="beforeLog" class="com.ray.logAspect.beforelog"/>
    <bean id="afterLog" class="com.ray.logAspect.afterlog" />


    <!--方式一：直接使用spring api接口-->
    <!--配置aop：首先导入sop约束-->
    <aop:config>
        <!--切入点：expression表达式，execution中添加要执行的位置-->
        <aop:pointcut id="pointcut" expression="execution(* com.ray.service.UserServiceImp.*(..))"/>

        <!--执行环绕增强-->
        <aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>


</beans>
```



5.测试实现

```java
import com.ray.service.UserService;
import com.ray.service.UserServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2020/4/11.
 */
public class MyTest {
    public static void main(String[] args) {

        //获取管理容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //默认jdk代理，是动态代理的是接口!
        UserService userService = context.getBean("userService", UserService.class);

        userService.add();
    }
}

```





## 十二、AOP实现方式二：使用自定义类实现【spring-09-aop】

### 12.1自定义且面类

```java
package com.ray.diylog;

/**
 * Created by Administrator on 2020/4/11.
 */
public class logPointcut {
    public void before(){
        System.out.println("================方法执行前================");
    }

    public void after(){
        System.out.println("================方法执行后================");
    }
}

```

### 12.2 配置applicationContext.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--<context:annotation-config/>-->

    <bean id="userService" class="com.ray.service.UserServiceImp"/>
    <bean id="beforeLog" class="com.ray.logAspect.beforelog"/>
    <bean id="afterLog" class="com.ray.logAspect.afterlog" />


    <!--方式一：直接使用spring api接口-->
    <!--配置aop：首先导入sop约束-->
    <!--<aop:config>-->
        <!--&lt;!&ndash;切入点：expression表达式，execution中添加要执行的位置&ndash;&gt;-->
        <!--<aop:pointcut id="pointcut" expression="execution(* com.ray.service.UserServiceImp.*(..))"/>-->

        <!--&lt;!&ndash;执行环绕增强&ndash;&gt;-->
        <!--<aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut"/>-->
        <!--<aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>-->
    <!--</aop:config>-->


    <!--方式二：自定义类-->
    <bean id="diyLogPoint" class="com.ray.diylog.logPointcut"/>

    <aop:config>
        <!--自定义切面，ref使用引用类-->
        <aop:aspect ref="diyLogPoint">
            <!--切入点-->
            <aop:pointcut id="point" expression="execution(* com.ray.service.UserServiceImp.*(..))"/>
            <!--通知-->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>


</beans>
```

### 12.3测试类：不变

```java
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

```





## 十三、AOP实现方式三：注解实现【spring-09-aop】

### 13.1自定义且面类

```java
package com.ray.diylog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by Administrator on 2020/4/11.
 * 注解实现
 */
@Aspect
public class AnnotationPointCut {

    @Before("execution(* com.ray.service.UserServiceImp.*(..))")
    public void beforelog(){
        System.out.println("================注解类实现：方法执行前================");
    }

    @After("execution(* com.ray.service.UserServiceImp.*(..))")
    public void afterlog(){
        System.out.println("================注解类实现：方法执行后================");
    }

    //在环绕增强中，可以给定一个参数，代表需要获取切入点的信息
    @Around("execution(* com.ray.service.UserServiceImp.*(..))")
    public void around(ProceedingJoinPoint pro) throws Throwable {
        System.out.println("环绕前");
        Object proceed=pro.proceed();
        System.out.println("环绕后");
    }

    /**
     * 执行顺序结果
     *
     *
         环绕前
         ================注解类实现：方法执行前================
         增加了一个用户
         环绕后
         ================注解类实现：方法执行后================
     */
}

```

### 13.2 配置xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--<context:annotation-config/>-->

    <bean id="userService" class="com.ray.service.UserServiceImp"/>
    <bean id="beforeLog" class="com.ray.logAspect.beforelog"/>
    <bean id="afterLog" class="com.ray.logAspect.afterlog" />


    <!--方式一：直接使用spring api接口-->
    <!--配置aop：首先导入sop约束-->
    <!--<aop:config>-->
        <!--&lt;!&ndash;切入点：expression表达式，execution中添加要执行的位置&ndash;&gt;-->
        <!--<aop:pointcut id="pointcut" expression="execution(* com.ray.service.UserServiceImp.*(..))"/>-->

        <!--&lt;!&ndash;执行环绕增强&ndash;&gt;-->
        <!--<aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut"/>-->
        <!--<aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>-->
    <!--</aop:config>-->


    <!--方式二：自定义类-->
    <!--<bean id="diyLogPoint" class="com.ray.diylog.logPointcut"/>-->

    <!--<aop:config>-->
        <!--&lt;!&ndash;自定义切面，ref使用引用类&ndash;&gt;-->
        <!--<aop:aspect ref="diyLogPoint">-->
            <!--&lt;!&ndash;切入点&ndash;&gt;-->
            <!--<aop:pointcut id="point" expression="execution(* com.ray.service.UserServiceImp.*(..))"/>-->
            <!--&lt;!&ndash;通知&ndash;&gt;-->
            <!--<aop:before method="before" pointcut-ref="point"/>-->
            <!--<aop:after method="after" pointcut-ref="point"/>-->
        <!--</aop:aspect>-->
    <!--</aop:config>-->


    <!--方式三-->
    <!--注册切面类，或者直接@Component也可-->
    <bean id="annotation" class="com.ray.diylog.AnnotationPointCut"/>
    <!--开启注解支持-->
    <!--动态代理实现：JDK(默认）proxy-target-class="false"  cglib：proxy-target-class="true"-->
    <aop:aspectj-autoproxy proxy-target-class="false"/>
</beans>

```

### 13.3 测试类不变

```java
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

```

