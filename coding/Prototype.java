public class Prototype {
    // 原型接口
    interface Prototype {
        Prototype clone();
    }

    // 具体原型类
    class ConcretePrototype implements Prototype {
        private String name;

        public ConcretePrototype(String name) {
            this.name = name;
        }

        // 复制自身的方法
        @Override
        public Prototype clone() {
            return new ConcretePrototype(this.name);
        }

        // 获取名称的方法
        public String getName() {
            return name;
        }
    }

    // 客户端代码
    public class PrototypePatternExample {
        public static void main(String[] args) {
            // 创建具体原型对象
            Prototype prototype = new ConcretePrototype("Prototype 1");

            // 复制原型对象来创建新的对象实例
            Prototype clonedPrototype = prototype.clone();

            // 输出新创建的对象实例的名称
            System.out.println("Cloned Prototype Name: " + ((ConcretePrototype) clonedPrototype).getName());
        }
    }
}
