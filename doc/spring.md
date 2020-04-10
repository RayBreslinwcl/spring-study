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

