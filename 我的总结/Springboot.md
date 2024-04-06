# Springboot

#### 1. 核心特性

Spring Boot 是一个基于 Spring 框架的开发框架，它致力于简化 Spring 应用的开发、配置和部署过程，提高开发效率和开发体验。Spring Boot 的核心特性包括：

1. **简化配置：** Spring Boot 采用**`约定大于配置`**的理念，通过默认配置和自动配置的方式，大大简化了应用程序的配置过程。开发者无需手动配置大量的 XML 或注解，而是采用默认配置和自动配置的方式，只需专注于业务逻辑的实现即可。
2. **自动配置：** Spring Boot 提供了丰富的自动配置功能，根据应用的依赖和环境自动配置 Spring 应用的各种组件，包括 Web MVC、数据源、事务管理器、消息队列等。开发者可以通过简单的依赖管理和配置文件来实现复杂的功能。
3. **启动器：** Spring Boot 提供了一系列 Starter 包，以 `spring-boot-starter-*` 的命名方式提供不同的功能模块，比如 Web、数据访问、安全等。启动器集成了相关的依赖和自动配置，可以简化项目的依赖管理和配置过程。
4. **内嵌容器：** Spring Boot 支持内嵌式的 Servlet 容器，包括 Tomcat、Jetty 和 Undertow。开发者无需手动部署 WAR 包到外部容器，而是可以将应用打包成可执行的 JAR 文件，并直接运行在内嵌的容器中，方便部署和管理。
5. **生产就绪性：** Spring Boot 提供了一系列功能和特性，帮助开发者构建生产就绪的应用，包括健康检查、度量指标、外部化配置、日志记录等。开发者可以通过这些功能来监控和管理应用的运行状态，保障应用的稳定性和可靠性。
6. **集成测试：** Spring Boot 提供了方便的测试支持，包括单元测试、集成测试和端到端测试。开发者可以使用 Spring Boot 的测试框架来编写和运行各种类型的测试，保证应用的质量和稳定性。
7. **生态系统：** Spring Boot 生态系统庞大丰富，有大量的第三方库和插件可供选择，可以方便地集成各种功能和服务，如数据库访问、消息队列、缓存、安全认证等。

#### 2. 自动配置机制

Spring Boot 的自动配置机制是 Spring Boot 框架的核心特性之一，它通过**`约定大于配置`**的方式，为开发者提供了便捷的应用程序配置和开发体验。**本质上就是Springboot自己通过一系列操作自动地去加载所有满足加载条件的Bean并将它们放到IoC容器中，而不用用户自己去注入。**

自动配置机制的核心思想是**根据应用的依赖和环境自动地配置 Spring 应用的各种组件**，使得开发者可以专注于业务逻辑的实现，而不必过多关注底层的配置细节。Spring Boot 自动配置机制的实现依赖于以下几个关键组件和技术：

1. **条件化配置（Conditional Configuration）：** Spring Boot 使用条件化配置的方式来根据应用的环境和依赖情况来决定是否需要配置某个组件。通过 `@Conditional` 注解和条件接口（`Condition`），可以在特定条件下进行配置。
2. **启动器（Starters）：** 启动器是 Spring Boot 提供的一组依赖管理和自动配置的 Maven 或 Gradle 项目依赖。它们以 `spring-boot-starter-*` 的命名方式提供不同的功能，比如 `spring-boot-starter-web`、`spring-boot-starter-data-jpa` 等。启动器可以简化项目的依赖管理，同时自动配置相应的组件。
3. **自动配置类（Auto-Configuration Classes）：** Spring Boot 提供了大量的自动配置类，用于自动配置各种 Spring 组件和第三方库。这些自动配置类使用 `@Configuration` 注解进行标记，并通过 `@Conditional` 注解进行条件化配置。自动配置类通常包含了一系列 `@Bean` 注解用于配置组件，比如数据源、Web MVC、事务管理器等。
4. **自动配置处理器（Auto-Configuration Processors）：** Spring Boot 使用自动配置处理器来扫描项目中的依赖和配置，并根据约定自动配置相应的组件。自动配置处理器负责从类路径下的 `META-INF/spring.factories` 文件中加载自动配置类，并根据条件进行自动配置。
5. **外部化配置（Externalized Configuration）：** Spring Boot 支持外部化配置，可以通过配置文件（如 `application.properties` 或 `application.yml`）来覆盖默认的自动配置。开发者可以在配置文件中设置特定的属性来修改默认配置，从而满足特定的需求。

- `@Configuration`标注在某个类上，表示这是一个 springboot的`配置类`。可以向容器中注入组件。

- `@ComponentScan`：配置用于 Configuration 类的组件扫描指令。

流程图：

![autoConfiguration](/Users/chocolate/Documents/project/call4jobs/我的总结/autoConfiguration.webp)

**@AutoConfigurationPackag** 的作用就是将SpringBoot主配置类所在的包及其下面的所有子包里面的所有组件扫描到 Spring 容器中。

**@Import(AutoConfigurationImportSelect.class)**：SpringBoot 在启动的时候就从类路径下的**META-INF/spring.factories** 中获取`EnableAutoConfiguration`指定的值，并**将这些值加载到自动配置类导入到容器中，自动配置类就生效**，帮助我们进行自动配置功能。 而这些自动配置类 全都在 **spring-boot-autoconfigure-2.2.6.RELEASE.jar** 该jar包之下。

总的来说，Spring Boot 的自动配置机制通过条件化配置、启动器、自动配置类、自动配置处理器和外部化配置等技术手段，实现了对 Spring 应用的自动化配置，极大地简化了应用程序的开发和部署过程，提高了开发效率和可维护性。

#### 3. bean的生命周期

1. **实例化（Instantiation）**：
   - 当 Spring 容器启动时，它会根据配置信息实例化 Bean。这可以通过**构造函数**或**工厂方法**来完成。
   - 如果 Bean 是通过构造函数来创建的，则 Spring 使用 Java 的**反射机制**直接调用 Bean 类的构造函数来实例化 Bean。
   - 如果 Bean 是通过工厂方法创建的，则 Spring 调用工厂方法来获取 Bean 的实例。
2. **属性设置（Properties Setting）**：
   - 在实例化之后，Spring 容器会通过**依赖注入**（Dependency Injection）或者**属性注入**（Property Injection）来设置 Bean 的属性值。
   - 这些属性通常是在 Spring 配置文件中或者通过注解指定的。
3. **初始化（Initialization）**：
   - 在属性设置完成后，Spring 容器会调用 Bean 的初始化方法。这个初始化方法可以是通过实现 `InitializingBean` 接口来指定的 `afterPropertiesSet()` 方法，也可以是使用 `init-method` 属性指定的自定义初始化方法。
   - 开发者可以在初始化方法中执行一些自定义的初始化逻辑，例如资源加载、连接数据库等。
4. **使用中（In Use）**：
   - 当 Bean 初始化完成后，它就可以被应用程序使用了。其他 Bean 或者组件可以通过依赖注入的方式获取该 Bean 的实例，并调用其方法。
5. **销毁（Destruction）**：
   - 当 Spring 容器关闭时，它会调用 Bean 的销毁方法来释放资源。这个销毁方法可以是通过实现 `DisposableBean` 接口来指定的 `destroy()` 方法，也可以是使用 `destroy-method` 属性指定的自定义销毁方法。
   - 在销毁方法中，开发者可以执行一些清理资源的操作，例如关闭数据库连接、释放文件资源等。

#### 4. Springboot如何创建、使用事务

1. pom中添加spring-tx依赖

   ```java
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-tx</artifactId>
       <version>5.2.7.RELEASE</version>
   </dependency>
   ```

2. 通过注解的方式(也可以通过xml或者Java配置类的方式，不过没有使用注解的方式快)开启你的SpringBoot应用对事务的支持。使用@EnableTransactionManagement注解（来自于上面引入的spring-tx包）

   ```java
   // Spring推荐的方式,是将@EnableTransactionManagement加到被@Configuration注解的类上
   @SpringBootApplication
   @EnableTransactionManagement
   public class Application {
   
   	public static void main(String[] args) {
   		SpringApplication.run(Application.class, args);
   	}
   }
   ```

3. @Transactional事务失效的情况：

   1. 数据库引擎不支持事务如MyIsam

   2. 使用@Transactional的类没有注入spring容器

   3. **被@Transactional注解的方法不是public**，**因为@Transactional是通过动态代理技术实现的**，而动态代理是从类的外部去获取类的实例对象，这个过程是无法调用类内部private方法所有会失效。

   4. 方法自调用，因为**默认只有在外部调用事务才会生效**，而方法自调用没有通过Spring的代理类。

      ```java
      // 下面两种方式都会出现自调用情况，事务都不会生效
      @Service
      public class serviceImpl implements Service {
        public void update() {
          updateTransaction();
        }
        @Transactional(propagation = Propagation.REQUIRES_NEW)
        public void updateTransaction() {
          //code
        };
      }
      
      @Service
      public class serviceImpl implements Service {
        @Transactional
        public void update() {
          updateTransaction();
        }
        @Transactional(propagation = Propagation.REQUIRES_NEW)
        public void updateTransaction() {
          //code
        };
      }
      ```

      

   5. 数据源没有配置事务管理器

#### 5. Spring的注解是什么，怎么使用

注解在 Java 中被定义为接口，其底层实现实际上是**一种特殊的接口**。在编译后，注解不会被编译成普通的类或接口，而是会被编译成一种特殊的类文件，其中包含了注解的信息以及与之相关的元数据。

当 Java 程序运行时，JVM 会加载类文件，并通过 Java 反射机制获取类、方法、字段等的元数据信息，其中也包括注解信息。通过反射，程序可以动态地获取注解，并根据注解的内容进行相应的逻辑处理。

在 Java 中，注解的底层实现主要依赖于**反射机制**。在编译期间，Java 编译器会解析和处理注解，但注解本身并不会直接影响编译后的代码，而是在运行时由 JVM 和 Java 反射机制进行处理。

> 在 Java 中，注解（Annotation）的本质上确实是一种特殊的接口。它们并不是普通的接口，而是由编译器特殊处理的一种接口。
>
> Java 的注解是由 `@interface` 关键字定义的，定义注解时可以指定一些元数据，比如是否可继承、是否支持重复注解等。注解本身并不包含任何方法的实现，而是**用于标记代码元素（类、方法、字段等）以及提供元数据信息**。
>
> Java 编译器在编译时会识别并处理注解，将注解的信息保存在编译后的类文件中。这些注解信息可以在运行时通过反射机制获取，并用于实现各种功能，比如依赖注入、AOP（面向切面编程）、配置文件处理等。
>
> 对于 Spring 框架中的 `@Autowired` 注解，其本质上就是一个由编译器特殊处理的接口。Spring 容器在启动时会扫描类路径下的所有类，并检查其中标记了 `@Autowired` 注解的字段、构造函数或者方法参数。然后根据注解的信息，自动装配符合条件的 Bean 到相应的位置。
>
> 总的来说，**注解本身并不直接完成这些功能，而是通过编译器和运行时的特殊处理，结合反射机制实现的**。它们提供了一种声明式的编程方式，使得开发者可以在代码中添加元数据信息，从而实现更加灵活和方便的功能扩展。

#### 6. Springboot约定大于配置怎么理解

在SpringBoot中，约定大于配置可以从以下两个方面来理解：

1. **开发人员仅需规定应用中不符合约定的部分**
2. **在没有规定配置的地方，采用默认配置，以力求最简配置为核心思想**

总的来说，上面两条都遵循了**推荐默认配置**的思想。当存在特殊需求的时候，自定义配置即可。这样可以大大的减少配置工作，这就是所谓的“约定”。

#### 7. Bean的创建方法

1. **XML配置文件加载**：通过XML配置文件（通常是applicationContext.xml）来定义和配置bean。在XML文件中使用 `<bean>` 元素来声明一个bean，指定其类名、属性等信息。Spring容器在启动时会加载这些XML文件，并根据配置来创建和管理相应的bean。

   ```xml
   <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <bean id="myBean" class="com.example.MyBean">
           <property name="propertyName" value="propertyValue"/>
       </bean>
   
   </beans>
   ```

2. **注解加载**：使用注解来标识bean，让Spring自动扫描并加载这些bean。通常使用 `@Component` 或其衍生注解（如 `@Service`、`@Repository`、`@Controller`）来标识一个类为一个Spring bean。

   ```java
   @Component
   public class MyBean {
       // Bean properties and methods
   }
   ```

   在Spring的配置类上使用 `@ComponentScan` 来启用自动扫描，并指定扫描的包路径。

   ```java
   @Configuration
   @ComponentScan("com.example")
   public class AppConfig {
       // Configuration settings
   }
   ```

3. **Java配置类加载**：通过Java配置类来定义和配置bean。通常创建一个带有 `@Configuration` 注解的类，使用 `@Bean` 注解来标识方法返回的对象应该被Spring容器管理。

   ```java
   @Configuration
   public class AppConfig {
       @Bean
       public MyBean myBean() {
           return new MyBean();
       }
   }
   ```


#### 8. `@Scheduled`注解的作用和使用方法

`@Scheduled`注解是spring boot提供的用于定时任务控制的注解,主要用于控制任务在某个指定时间执行,或者每隔一段时间执行.注意需要配合`@EnableScheduling`使用

#### 9. IoC

控制反转，将对象的创建进行反转。通常对象是由开发者自己创建，使用IoC可以根据配置文件根据需求自动创建所需对象。

#### 10. AOP

面向切面编程，抽象化的面向对象，AOP是基于IoC和动态代理机制实现的。在方法相似的位置执行相似的操作，将这些相似的位置抽象出来作为一个切面对象，对这个切面对象进行开发，从而在多个方法的相同位置都会执行统一的方法。

将模块化方法从业务功能中抽离，最后实现的时候结合业务对象和aop对象生成一个代理对象。

AOP功能：

- 打印日志
- 执行事务
- 权限管理

实现过程：

1. 创建切面累类

2. 创建切面方法，使用`JoinPoint`连接点获取原函数的参数和方法名

3. 使用`@Before`、`AfterReturing`

   ```java
   @Component
   @Aspect
   public class Aspect {
     @Before("execution(public int 完全类名 (..))")
     public void before(JoinPoint joinPoint) {
       String name = joinPoint.getSignature().getName();
       sout(name + joinPoint.getArgs());
     }
     
     @After是在方法执行之后但是在返回之前，所以拿不到返回值
     
     @AfterReturing(value="execution(public int 完全类名 (..))", returning = "result")
     public void afterReturning(JoinPoint joinPoint, Object result) {
       String name = joinPoint.getSignature().getName();
       sout(name + result);
     }
   }
   ```

4. 配置自动扫包，开启自动生成代理对象。在`spring.xml`中配置扫描包的路径，并开启`aopAutoProxy`

#### 11. lombok

在实体类上使用`@Data`注解可以帮助我们自动生成`get()`、`set()`、`toString()`、构造方法。