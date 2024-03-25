public class Factory {
    // 简单工厂模式
    public static Factory simpleFactory;
    public interface Product {
        void function();
    }
    class ProductA implements Product {
        @Override
        public void function() {
            System.out.println("this is product A");
        }
    }
    class ProductB implements Product {
        @Override
        public void function() {
            System.out.println("this is product B");
        }
    }
    public Product create(String type) {
        if(type == "A") {
            return new ProductA();
        } else if(type == "B") {
            return new ProductB();
        } else {
            throw new IllegalArgumentException();
        }
    }
    public static Factory getFactory() {
        simpleFactory = new Factory();
        return simpleFactory;
    }
    public static void main(String[] args) {
        Factory simpleFactory = Factory.getFactory();
        Product product = simpleFactory.create("A");
        product.function();
    }
}
