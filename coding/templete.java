public class templete {
    // 抽象模板类
    abstract class AbstractTemplate {
        // 模板方法，定义了算法的骨架
        public final void templateMethod() {
            // 步骤1：执行通用操作
            System.out.println("Step 1: Common operation");

            // 步骤2：调用抽象方法
            abstractMethod();

            // 步骤3：执行通用操作
            System.out.println("Step 3: Common operation");
        }

        // 抽象方法，由子类实现
        protected abstract void abstractMethod();
    }

    // 具体模板类A
    class ConcreteTemplateA extends AbstractTemplate {
        // 实现了抽象方法
        @Override
        protected void abstractMethod() {
            System.out.println("Step 2A: Concrete operation for Template A");
        }
    }

    // 具体模板类B
    class ConcreteTemplateB extends AbstractTemplate {
        // 实现了抽象方法
        @Override
        protected void abstractMethod() {
            System.out.println("Step 2B: Concrete operation for Template B");
        }
    }

    // 客户端代码
    public class TemplatePatternExample {
        public static void main(String[] args) {
            // 创建具体模板对象A，并调用模板方法
            AbstractTemplate templateA = new ConcreteTemplateA();
            templateA.templateMethod();

            System.out.println();

            // 创建具体模板对象B，并调用模板方法
            AbstractTemplate templateB = new ConcreteTemplateB();
            templateB.templateMethod();
        }
    }

}
