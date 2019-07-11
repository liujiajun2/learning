package xin.liujiajun.guava.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-13 17:48
 **/
public class AnimalInvocationHandler implements InvocationHandler {

    private Animal animal;
    public AnimalInvocationHandler(Animal animal){
        this.animal = animal;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("hhhh");
        method.invoke(animal,args);
        return proxy;
    }
}
