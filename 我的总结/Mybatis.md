# Mybatis知识

##### 1. Mybatis是什么？

Mybatis是一个**轻量级持久层框架**(完成数据持久化工作的代码块)，它支持自定义sql、存储过程、高级映射，同时避免了几乎所有JDBC代码编写、参数设置和获取结果集的工作。MyBatis 可以通过简单的 **XML** 或**注解**来配置和映射**原始类型**、**接口**和 **Java POJO**（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。



##### 2. `UserMapper mapper = sqlSession.getMapper(UserMapper.class);`实现原理？

1. **SqlSession**: `sqlSession` 是 MyBatis 中执行 SQL 的关键对象。它是一个接口，提供了各种执行 SQL 语句的方法，比如查询、插入、更新等。SqlSession 是通过 SqlSessionFactory 创建的。

2. **SqlSessionFactory**: `SqlSessionFactory` 是 MyBatis 的关键对象之一。它是一个工厂类，用于创建 SqlSession 实例。SqlSessionFactory 使用 MyBatis 的配置信息来构建 SqlSession 实例。

3. **Mapper接口**: `UserMapper` 接口是一个定义了数据库操作方法的接口，其中包含了一些针对用户对象进行操作的方法，比如查询用户、插入用户等。这些方法通常是通过在接口中定义 SQL 查询语句来实现的。

4. **Mapper接口的实现**: 通常，`UserMapper` 接口并没有具体的实现代码，而是由 MyBatis 在运行时根据配置文件和接口定义动态生成的。这个过程叫做**动态代理**。MyBatis 会根据接口定义以及配置文件中的 SQL 映射，动态生成接口的实现代码，并且将 SQL 的执行与 Java 方法的调用结合起来。

5. **getMapper 方法**: `getMapper(UserMapper.class)` 是 SqlSession 接口提供的方法之一，用于获取指定 Mapper 接口的实例。当调用这个方法时，**MyBatis 会根据传入的 Mapper 接口类型动态生成这个接口的实现类，并返回一个实现了该接口的代理对象。**

综上所述，这段代码的实现原理是利用了 MyBatis 框架提供的**动态代理机制**，根据 Mapper 接口的定义和配置文件中的 SQL 映射，**动态生成了 Mapper 接口的实现类**，从而将 Java 方法的调用与 SQL 的执行进行了结合。



##### 3. `mapUnderscoreToCamelCase`开启驼峰命名

```xml
<setting name="mapUnderscoreToCamelCase" value="true"/>
```

即从经典数据库列名 A_COLUMN 映射到经典 Java 属性名 aColumn。

数据库中不使用驼峰命名而是使用下划线`_`的原因是有些数据库会将列名全部转为大写字母。



##### 4. mapper映射器的绑定注册方式

总共有四种方法：

- 使用xml资源路径（最推荐）

  ```xml
  <!-- 使用相对于类路径的资源引用 -->
  <mappers>
    <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
    <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
    <mapper resource="org/mybatis/builder/PostMapper.xml"/>
  </mappers>
  ```

- 使用完全限定的接口名（有限制）

  接口和对应配置文件mapper必须同名

  接口和对应配置文件mapper必须位于同一package下

  ```xml
  <!-- 使用映射器接口实现类的完全限定类名 -->
  <mappers>
    <mapper class="org.mybatis.builder.AuthorMapper"/>
    <mapper class="org.mybatis.builder.BlogMapper"/>
    <mapper class="org.mybatis.builder.PostMapper"/>
  </mappers>
  ```

- 使用包名（限制同上）

  ```xml
  <!-- 将包内的映射器接口全部注册为映射器 -->
  <mappers>
    <package name="org.mybatis.builder"/>
  </mappers>
  ```

- 使用url（不推荐）

  ```xml
  <!-- 使用完全限定资源定位符（URL） -->
  <mappers>
    <mapper url="file:///var/mappers/AuthorMapper.xml"/>
    <mapper url="file:///var/mappers/BlogMapper.xml"/>
    <mapper url="file:///var/mappers/PostMapper.xml"/>
  </mappers>
  ```

  

##### 5. Mybatis注解底层还是反射机制。

##### 6. 动态SQL

if、choose、when、otherwise、trim、foreach

##### 7. Mybatis的缓存

缓存：存在内存中的临时数据，减小程序查询数据库的次数，提高效率。

什么数据适合放在缓存：经常查询、不经常改变的数据。

Mybatis系统默认定义了两级缓存：**一级缓存**和**二级缓存**。

一级缓存默认开启（SqlSession级别的缓存，本地缓存）

二级缓存需要手动开启，基于namespace（一个mapper中）级别的缓存。

##### 8. 一级缓存

- **映射语句文件中的所有 select 语句的结果将会被缓存**。
- 映射语句文件中的所有 insert、update 和 delete 语句会**刷新缓存**。
- 缓存会使用最近最少使用算法（LRU, Least Recently Used）算法来清除不需要的缓存。
- 缓存不会定时进行刷新（也就是说，**没有刷新间隔**）。
- 缓存会保存列表或对象（无论查询方法返回哪种）的 **1024** 个引用。
- 缓存会被视为读缓存（或写缓存），这意味着获取到的对象并**不是共享的**，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改。
- sqlSession.clearCache()可以手动刷新缓存。

##### 9. 二级缓存

二级缓存也叫**全局缓存**。同一个namespace（mapper）下的所有操作语句，都影响着一个共同的Cache。

工作机制：

​	当sqlSession关闭时，一级缓存会被保存到二级缓存中；

​	新的会话可以从二级缓存中获取内容；

​	不同的mapper查询的数据会放在自己对应的缓存中。

```xml
<cache
  eviction="FIFO"
  flushInterval="60000"
  size="512"
  readOnly="true"/>
```

Eviction——清除缓存的算法（FIFO、LRU（默认））

flushInterval——刷新间隔

size——引用数目（默认1024）

readOnly——是否是只读缓存

​	**MyBatis的二级缓存不适应用于映射文件中存在多表查询的情况**。由于MyBatis的二级缓存是基于`namespace`的，多表查询语句所在的`namspace`无法感应到其他`namespace`中的语句对多表查询中涉及的表进行的修改，引发脏数据问题。

​	使用`cache-ref`可以将两个映射文件对应的SQL操作都使用的是同一块缓存了。

```xml
<cache-ref namespace="com.someone.application.data.SomeMapper"/>
```

##### 10. 缓存查询顺序

<img src="/Users/chocolate/Documents/project/call4jobs/我的总结/mybatis_cache.png" style="zoom:50%;" />

​	开启二级缓存后，使用CachingExecutor装饰Executor，进入一级缓存查询流程前，会现在CachingExecutor进行二级缓存查询。

​	查询顺序为：二级缓存——>一级缓存——>数据库。

