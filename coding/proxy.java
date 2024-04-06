public class proxy {
    // 抽象主题接口
    interface Subject {
        void request();
    }

    // 真实主题类
    class RealSubject implements Subject {
        @Override
        public void request() {
            System.out.println("RealSubject: Handling request.");
        }
    }

    // 代理类
    class Proxy implements Subject {
        private RealSubject realSubject;

        public Proxy() {
            this.realSubject = new RealSubject();
        }

        @Override
        public void request() {
            // 在访问真实主题之前可以执行一些额外的操作
            System.out.println("Proxy: Logging before request.");

            // 通过真实主题处理请求
            realSubject.request();

            // 在访问真实主题之后可以执行一些额外的操作
            System.out.println("Proxy: Logging after request.");
        }
    }

    // 客户端代码
    public class ProxyPatternExample {
        public static void main(String[] args) {
            // 创建代理对象
            Proxy proxy = new Proxy();

            // 通过代理对象访问真实主题对象
            proxy.request();
        }
    }

}
