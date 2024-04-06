public class strategy {
    // 策略接口
    interface Strategy {
        void execute();
    }

    // 具体策略类：策略A
    class ConcreteStrategyA implements Strategy {
        @Override
        public void execute() {
            System.out.println("Executing strategy A.");
        }
    }

    // 具体策略类：策略B
    class ConcreteStrategyB implements Strategy {
        @Override
        public void execute() {
            System.out.println("Executing strategy B.");
        }
    }

    // 上下文类
    class Context {
        private Strategy strategy;

        public Context() {}

        // 设置策略
        public void setStrategy(Strategy strategy) {
            this.strategy = strategy;
        }

        // 执行策略
        public void executeStrategy() {
            strategy.execute();
        }
    }

    // 客户端代码
    public class StrategyPatternExample {
        public static void main(String[] args) {
            // 创建上下文对象，并设置具体策略A
            Context context = new Context();

            context.setStrategy(new ConcreteStrategyA());

            // 执行策略A
            context.executeStrategy();

            // 切换至具体策略B
            context.setStrategy(new ConcreteStrategyB());

            // 执行策略B
            context.executeStrategy();
        }
    }

}
