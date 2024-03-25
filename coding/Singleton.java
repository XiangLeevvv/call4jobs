public class Singleton {
    private static Singleton singleton = null;
    private Singleton() {};
    
    // 单线程安全的单例模式
    public static Singleton getInstance() {
        if(singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    // 多线程安全的单例模式
    // 双重检查锁定机制
    public static Singleton getInstanceInMultiThread() {
        if(singleton != null) {
            return singleton;
        }
        // 这种方式也叫做类级别的锁定（Class-level Locking）
        // 因为每个类的Class对象都是唯一的，所以在多线程环境下，使用类的Class对象作为锁可以保证线程安全。
        synchronized(Singleton.class) {
            if(singleton == null) {
                singleton = new Singleton();
            }
            return singleton;
        }
    }

    // 使用静态内部类实现单例模式，既保证线程安全又保证延迟加载和高效率
    private static class SingletonHolder {
        // 在静态初始化中创建单例实例
        // 静态初始化是线程安全的，同时JVM在加载类时只会进行一次静态初始化，保证了实例的唯一性
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstanceWithinClass() {
        return SingletonHolder.INSTANCE;
    }
}