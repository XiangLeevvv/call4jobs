// 工厂模式关注单个产品等级结构的对象创建，而抽象工厂模式关注多个产品族的对象创建。
// 工厂模式只需定义一个工厂类，而抽象工厂模式需要定义多个具体工厂类，每个具体工厂类负责创建一个产品族的对象。
public class AbstractFactory {
    // 抽象产品接口A
interface AbstractProductA {
    void use();
}

// 具体产品A1
class ConcreteProductA1 implements AbstractProductA {
    @Override
    public void use() {
        System.out.println("ConcreteProductA1 in use.");
    }
}

// 具体产品A2
class ConcreteProductA2 implements AbstractProductA {
    @Override
    public void use() {
        System.out.println("ConcreteProductA2 in use.");
    }
}

// 抽象产品接口B
interface AbstractProductB {
    void operate();
}

// 具体产品B1
class ConcreteProductB1 implements AbstractProductB {
    @Override
    public void operate() {
        System.out.println("ConcreteProductB1 operated.");
    }
}

// 具体产品B2
class ConcreteProductB2 implements AbstractProductB {
    @Override
    public void operate() {
        System.out.println("ConcreteProductB2 operated.");
    }
}
// 核心
// 抽象工厂接口
interface abstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}

// 具体工厂1，用于创建产品族1
class ConcreteFactory1 implements abstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ConcreteProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ConcreteProductB1();
    }
}

// 具体工厂2，用于创建产品族2
class ConcreteFactory2 implements abstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ConcreteProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ConcreteProductB2();
    }
}

// 客户端
public static void main(String[] args) {
    // 创建具体工厂1
    abstractFactory factory1 = new ConcreteFactory1();
    AbstractProductA productA1 = factory1.createProductA();
    AbstractProductB productB1 = factory1.createProductB();
    productA1.use();
    productB1.operate();

    // 创建具体工厂2
    abstractFactory factory2 = new ConcreteFactory2();
    AbstractProductA productA2 = factory2.createProductA();
    AbstractProductB productB2 = factory2.createProductB();
    productA2.use();
    productB2.operate();
}

}
