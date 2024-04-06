public class adpter {
    // 目标接口
    interface Target {
        void request();
    }

    // 被适配对象
    class Adaptee {
        public void specificRequest() {
            System.out.println("Adaptee: Specific request.");
        }
    }

    // 适配器类（类适配器）
    class Adapter extends Adaptee implements Target {
        @Override
        public void request() {
            // 调用被适配对象的方法来实现目标接口的功能
            specificRequest();
        }
    }

    // 客户端代码
    public class AdapterPatternExample {
        public static void main(String[] args) {
            // 创建适配器对象
            Target adapter = new Adapter();

            // 调用目标接口的方法（实际上会调用被适配对象的方法）
            adapter.request();
        }
    }

}
