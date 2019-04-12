package xin.liujiajun.java.thread;
//关于懒汉式线程安全问题，使用同步机制
//对于一般方法，可以用this
//对于静态方法，使用当前类本身
class Singleton{
    private Singleton(){
    }
    private static Singleton instance = null;

    public static Singleton getInstance() {
        if(instance == null){
            synchronized(Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
public class TestSingleton {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
        Class clazz = Singleton.class;
    }
}
