package xin.liujiajun.java.reflection;

interface ClothFactory{
    void productCloth();
}
//被代理类
class NikeClothFactory implements ClothFactory{
    public void productCloth() {
        System.out.println("Nike工厂生产一批衣服");
    }
}
//代理列
class ProxyFactory implements ClothFactory{

    ClothFactory factory;
    //创建代理类对象时，传入被代理类对象
    public ProxyFactory(ClothFactory factory) {
        this.factory = factory;
    }

    public void productCloth() {
        System.out.println("代理类开始执行");
        factory.productCloth();
    }
}

/**]
 * 静态代理
 */
public class TestClothProduct {
    public static void main(String[] args) {
        NikeClothFactory nike = new NikeClothFactory();//创建被代理类对象
        ProxyFactory proxy = new ProxyFactory(nike);//创建代理类的对象
        proxy.productCloth();
    }
}
