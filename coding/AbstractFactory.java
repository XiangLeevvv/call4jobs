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
interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}

// 具体工厂1，用于创建产品族1
class ConcreteFactory1 implements AbstractFactory {
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
class ConcreteFactory2 implements AbstractFactory {
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
public class AbstractFactoryPatternExample {
    public static void main(String[] args) {
        // 创建具体工厂1
        AbstractFactory factory1 = new ConcreteFactory1();
        AbstractProductA productA1 = factory1.createProductA();
        AbstractProductB productB1 = factory1.createProductB();
        productA1.use();
        productB1.operate();

        // 创建具体工厂2
        AbstractFactory factory2 = new ConcreteFactory2();
        AbstractProductA productA2 = factory2.createProductA();
        AbstractProductB productB2 = factory2.createProductB();
        productA2.use();
        productB2.operate();
    }
}

}
