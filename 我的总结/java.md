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