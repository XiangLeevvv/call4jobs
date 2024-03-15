# java

#### 1. java反射机制（`java.lang.reflect`）

​	Java的反射机制是指**在运行时动态地获取类的信息以及操作类的属性、方法和构造函数的能力**。通过反射，可以在运行时检查类、实例化对象、调用方法、获取和设置字段的值，而无需在编译时知道这些类的具体信息。以下是使用反射的一些常见用途：

1. **获取类的信息：** 可以通过反射获取类的名称、父类、接口、构造函数、方法和字段等信息。
2. **实例化对象：** 可以使用反射来实例化对象，即使在编译时无法确定类的具体类型。
3. **调用方法：** 可以动态地调用类的方法，包括公共方法、私有方法以及静态方法。
4. **操作字段：** 可以获取和设置对象的字段的值，包括公共字段、私有字段以及静态字段。
5. **动态代理：** 可以使用反射来创建动态代理对象，以实现在运行时生成代理类。

#### 2. java的依赖注入（DI——Dependency Injection）

- 依赖注入是一种**设计模式**，也是Spring框架的核心概念之一。

- 作用是去除Java类之间的依赖关系，实现松耦合，减少依赖性，增强可重用性，增加可读性、可扩展性。

  ```java
  // 紧耦合
  class Player{  
      Weapon weapon;  
  
      Player(){  
          // 与 Sword类紧密耦合
          this.weapon = new Sword();  
  
      }  
  
      public void attack() {
          weapon.attack();
      }
  }
  // 松耦合
  class Player{  
      Weapon weapon;  
  
      // weapon 被注入进来
      Player(Weapon weapon){  
          this.weapon = weapon;  
  
      }  
  
      public void attack() {
          weapon.attack();
      }
  
      public void setWeapon(Weapon weapon){  
          this.weapon = weapon;  
      }  
  }
  ```

- 通常有三种依赖注入的方式：

  - 构造器注入（常用）

    ```java
    /**
     * 电脑类
     */
    public class Computer {
    
       /**
        * 电脑的打印机
        */
       private Printer printer;
       
       // 省略getter和setter
    
    }
    public class Computer {
    
       /**
        * 电脑的打印机
        */
       private Printer printer = new Printer();
    
    }
    
    /**
     * 电脑类带参构造器
     * @param printer 传入打印机实例
     */
    public Computer(Printer printer) {
       // 构造器注入
       this.printer = printer;
    }
    
    Printer printer = new Printer();
    // 实例化电脑类，通过构造器注入了打印机实例（依赖）
    Computer computer = new Computer(printer);
    computer.getPrinter().print("构造器注入");
    ```

  - setter注入（常用）

    ```java
    public void setPrinter(Printer printer) {
       this.printer = printer;
    }
    
    Printer printer = new Printer();
    // 实例化电脑类
    Computer computer = new Computer();
    // 通过setter方法注入了打印机实例（依赖）
    computer.setPrinter(printer);
    computer.getPrinter().print("setter注入");
    ```

  - 接口注入

#### 3. 面向对象编程（OOP）

在Java中，你可以**创建类来表示现实世界中的实体或概念**，每个类可以包含数据（成员变量）和方法（成员方法）。然后，你可以使用这些类来创建对象，这些对象可以相互交互并执行特定的任务。例如，你可以创建一个名为`Person`的类，表示人，该类可能包含姓名、年龄等属性，以及吃饭、睡觉等方法。然后，你可以创建多个`Person`对象，每个对象代表一个实际的人，他们可以执行不同的操作。

面向对象编程提供了一种**更加模块化**、**可维护**和**可扩展**的方式来编写代码，**因为它将代码组织成小的、可重用的模块（类和对象），这些模块可以相互协作完成任务。**

#### 4. JDBC

JDBC（Java Database Connectivity）是Java语言中用于与数据库进行交互的一种**标准API**（应用程序编程接口）。**它允许Java应用程序通过SQL语句来执行数据库操作**，如查询、插入、更新和删除数据，以及管理数据库连接等。

除了使用JDBC（Java Database Connectivity），Java还提供了其他访问数据库的方法和框架。以下是一些主要的替代方案：

- #####  Java Persistence API (JPA)

​	JPA是一种用于ORM（对象关系映射）的Java标准，它允许开发人员通过**将Java类映射到关系数据库中的表来管理数据库数据**。最常见的JPA实现是**Hibernate**，它提供了强大的对象关系映射功能，简化了数据库操作。

- ##### Spring Data

​	Spring Data是Spring框架的一部分，它简化了与各种数据存储的交互，包括关系型数据库、NoSQL数据库和其他数据源。Spring Data提供了对JPA、MongoDB、Redis等数据存储的抽象和简化的访问方式。

- ##### MyBatis

​	MyBatis是一个Java持久层框架，它通过XML或注解的方式将SQL语句和Java方法进行映射，提供了灵活而强大的数据库操作功能。MyBatis允许开发人员直接编写原生SQL，同时提供了许多方便的特性，如动态SQL和自动映射。

#### 5. 乐观锁和悲观锁的理解及如何实现，有哪些实现方式？

- 悲观锁（**每次读取数据或者修改数据时都给数据上锁**）：

  总是假设最坏的情况，每次去拿数据的时候都认为别人会修改，所以**每次在拿数据的时候都会上锁**，这样别人想拿这个数据就会阻塞直到它拿到锁。传统的关系型数据库里边就用到了很多 这种锁机制，比如**行锁**，**表锁**等，**读写锁**等，都是在做操作之前先上锁。再比如Java里 面的同步原语**synchronized**关键字的实现也是悲观锁。

- 乐观锁（**读取数据时不会上锁，但更新时需要判断数据是否发生变化**）：

  顾名思义，就是很乐观，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用**版本号**等机制。乐观锁适 用于多读的应用类型，这样可以**提高吞吐量**，像数据库提供的类似于write_condition机制， 其实都是提供的乐观锁。在Java中java.util.concurrent.atomic包下面的原子变量类就是使用了乐观锁的一种实现方式**CAS**实现的。

#### 6. 一个对象的两个方法加 synchronized，一个线程进去 sleep，另一个线程可以进入到另一个方法吗？

如果一个对象的两个方法都被 `synchronized` 关键字修饰，那么在同一时间只有一个线程可以访问这个对象的任何一个被 `synchronized` 修饰的方法。这是**因为 `synchronized` 锁住的是对象，而不是方法**。

#### 7. 线程池拒绝策略有哪些？

1. **Abort Policy（默认）：** 当线程池无法处理新提交的任务时，将抛出 `RejectedExecutionException` 异常。这是线程池的默认拒绝策略。
2. **Discard Policy：** 当线程池无法处理新提交的任务时，**将丢弃新任务而不发出任何警告或异常**。这可能会导致任务丢失，不推荐在需要保证任务不丢失的情况下使用。
3. **Discard Oldest Policy：** 当线程池无法处理新提交的任务时，将**丢弃队列中等待时间最长的任务**（即最早提交的任务），然后尝试重新提交新任务。这个策略可以避免 `Discard Policy` 中的任务丢失，但仍然可能导致旧任务被丢弃。
4. **Caller Runs Policy：** 当线程池无法处理新提交的任务时，将使用提交任务的线程（即**调用线程**）来执行该任务。这种策略可以保证任务不会被丢弃，但可能会降低系统的性能。

#### 8. java是值传递

Java 中的参数传递是**按值传递**。这意味着当你调用一个方法时，方法得到的是实际参数值的一个拷贝，而不是参数本身。因此，如果在方法中修改了参数的值，这种改变不会影响原始参数的值。

**然而需要注意的是，对于引用类型的参数，传递的是引用的拷贝，而不是引用本身。这意味着如果你在方法中修改了引用指向的对象的属性，这种改变会影响原始对象的状态。**但是，如果你在方法中修改了引用指向一个新的对象，原始引用不会受到影响。

这种行为可能会导致一些误解，因为在使用引用类型时，通常我们看到的是引用指向的对象，而不是引用本身。但是，实际上，**在传递参数时，传递的是引用的拷贝，因此 Java 中的参数传递被认为是按值传递的。**

#### 9. java不可变类

> 不可变类是指**一旦创建后其状态（即对象的属性）就不能被修改的类**。这种类的实例是不可变的，即它们的状态在创建后不能被更改。在Java中，String类是一个典型的不可变类的例子。

不可变类具有以下特征：

1. **状态不可变性（State Immutability）**：一旦对象被创建，其状态就不能被更改。
2. **线程安全性（Thread Safety）**：由于对象的状态不可更改，因此它们在多线程环境中是安全的，不需要额外的同步。
3. **安全性和保护性（Security and Integrity）**：由于对象的状态不可更改，因此不可变类可以确保对象的完整性和安全性，避免了对象状态被意外或恶意修改的可能性。
4. **缓存友好性（Cacheability）**：不可变对象可以被安全地用作缓存键，因为它们的哈希码和相等性在对象的生命周期内是不变的。

要创建不可变类，通常需要采取以下措施：

- 声明类为`final`，以防止其被子类继承。
- 将类的所有属性声明为`private`，以防止直接访问。
- 不提供修改状态的方法（即不提供修改属性值的setter方法）。
- 如果类包含可变对象引用，应该确保不可变类不会泄漏对这些对象的引用，并且在构造函数中进行适当的防御性拷贝。

#### 10. synchronized

`synchronized` 关键字在多线程访问共享数据时同一时刻只能由单个线程抢到资源去执行。且在线程切换时，涉及到操作系统内核态与用户态的切换，这些操作会消耗额外的资源，因此效率较低。

`Synchronized` 的主要特点包括：

- `synchronized`实现的是**隐式锁**。
- **原子性（Atomicity）：** 由于 `synchronized` 关键字确保了同一时刻只有一个线程可以执行同步代码块或方法，因此它可以保证相关操作的原子性，即这些操作不会被其他线程中断。
- **可见性（Visibility）：** 当一个线程获得了锁并进入了同步代码块或方法时，它会将修改的变量的值刷新到主内存中，其他线程会看到这些变化。这就确保了变量的可见性。
- **互斥性（Mutual Exclusion）：** 当一个线程获得了对象的锁并进入了同步代码块或方法时，其他线程必须等待该线程释放锁才能继续执行同步代码块或方法。这就确保了对共享资源的互斥访问。

Java 中的每⼀个对象都可以作为锁，有三种加锁的⽅式：

- 对于**普通同步⽅法**，锁是**当前实例对象**。
- 对于**静态同步⽅法**，锁是当前类的 **Class 对象**
- 对于**同步⽅法块**，锁是 **Synchonized 括号⾥配置的对象**。

#### 11. 显示锁和隐式锁

1. **显示锁（Explicit Lock）**：
   - 显示锁是通过 `java.util.concurrent.locks` 包中的 Lock 接口及其实现类来实现的，比如 ReentrantLock。
   - 使用显示锁时，需要**手动地获取锁和释放锁，**即通过调用 `lock()` 方法获取锁，通过调用 `unlock()` 方法释放锁。
   - 显示锁提供了**更多的灵活性**，如可以实现公平锁、可中断锁、尝试获取锁等特性。
   - 显示锁适用于需要更细粒度的锁控制、更复杂的同步需求的情况。
2. **隐式锁（Implicit Lock）**：
   - 隐式锁通常是通过 `synchronized` 关键字实现的。
   - 在使用 synchronized 块时，**锁的获取和释放是隐式进行的**，即**进入 synchronized 块时自动获取锁，退出 synchronized 块时自动释放锁**。
   - synchronized 关键字的**锁是与对象关联**的，即每个对象都有一个**内置锁**（也称为**监视器锁**或**互斥锁**），线程需要获取对象的锁才能进入 synchronized 块。
   - 隐式锁相对于显示锁来说，语法更简单，使用更方便，但提供的功能和灵活性相对较少。

#### 12. 自旋锁

在Java中，自旋锁是一种**非阻塞的锁**，**它通过反复检查锁状态来避免线程阻塞**。当一个线程尝试获取锁时，如果锁已经被其他线程持有，那么这个线程不会立即阻塞，而是会在一个循环中反复尝试获取锁，直到获取到为止，或者达到一定的尝试次数。

```java
import java.util.concurrent.atomic.AtomicReference;

// 在这个示例中，AtomicReference被用来存储当前持有锁的线程。lock() 方法中，通过循环不断尝试将当前线程设置为持有者，直到成功设置为止。unlock() 方法则是将当前线程持有的锁释放。
public class SpinLock {
    private AtomicReference<Thread> owner = new AtomicReference<>();

    public void lock() {
        Thread currentThread = Thread.currentThread();
        while (!owner.compareAndSet(null, currentThread)) {
            // 如果锁已经被其他线程持有，则一直尝试获取锁
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        owner.compareAndSet(currentThread, null);
    }
}
```

需要注意的是，**自旋锁适用于锁被持有的时间非常短暂的情况**。如果持有锁的时间较长，那么自旋的线程会一直占用CPU资源，导致性能下降。因此，自旋锁一般在锁的**持有时间很短**，**且竞争不激烈**的情况下才适用。

`java.util.concurrent.atomic.AtomicInteger` 的 `compareAndSet` 方法可以用来实现简单的自旋锁。