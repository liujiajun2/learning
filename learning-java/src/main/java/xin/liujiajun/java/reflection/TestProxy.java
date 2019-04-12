package xin.liujiajun.java.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Subject{
    void action();
}
class ReelSubject implements Subject{

    public void action() {
        System.out.println("我是被代理类");
    }
}
class MyInvocationHandle implements InvocationHandler{
    Object object;//实现了接口的被代理类的对象的声明
    public Object blind(Object object){
        this.object = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }
    //当通过代理类的对象发起对被重写的方法的调用时，都会转换为对incoke方法的调用
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnValue = method.invoke(object,args);
        return returnValue;
    }
}
public class TestProxy {
    public static void main(String[] args) {
        ReelSubject reel = new ReelSubject();

        MyInvocationHandle handle = new MyInvocationHandle();

        Object obj = handle.blind(reel);
        Subject sub = (Subject)obj;//此时sub就是代理类的对象

        sub.action();//转到对实现类invoke（）接口的调用

        //再举一个例子
        NikeClothFactory nike  = new NikeClothFactory();
        ClothFactory cloth = (ClothFactory)handle.blind(nike);
        cloth.productCloth();
    }
}
