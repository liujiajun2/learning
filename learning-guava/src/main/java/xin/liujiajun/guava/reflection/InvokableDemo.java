package xin.liujiajun.guava.reflection;

import com.google.common.reflect.Invokable;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author liujiajun
 * @description Invokable demo
 * @create 2019-03-14 08:47
 **/
public class InvokableDemo {

    public static void main(String[] args) {

        Class<Object> objectClass = Object.class;
        Method[] methods = objectClass.getMethods();
        for (Method m : methods) {
            String name = m.getName();
            Invokable<?, Object> invokable = Invokable.from(m);

            //JDK判断方法是不是public
            System.out.println("JDK 判断方法是不是publie " + name + ": " + Modifier.isPublic(m.getModifiers()));
            //Invokable
            System.out.println("Invokable 判断方法是不是publie " + name + ": " + invokable.isPublic());
            //JDK判断方法是不是package private
            System.out.println("JDK 判断方法是不是package private " + name + ": " + ( !( Modifier.isPrivate(m.getModifiers()) || Modifier.isPublic(m.getModifiers()) ) ));
            //Invokable
            System.out.println("Invokable 判断方法是不是package private " + name + ": " + invokable.isPackagePrivate());
            //JDK 判断 方法第一个参数是否被定义了注解@Nullable
            boolean flag = false;
            int length = m.getParameterAnnotations().length;
            if(length <= 0) {
                continue;
            }
            Annotation[] parameterAnnotation = m.getParameterAnnotations()[0];
            for (Annotation annotation : parameterAnnotation) {
                if (annotation instanceof Nullable){
                    flag = true;
                }
            }
            System.out.println("JDK judge " + name + ": " + "第一个参数是否被定义了注解@Nullable" + flag);
            //invokable
            boolean annotationPresent = invokable.getParameters().get(0).isAnnotationPresent(Nullable.class);
            System.out.println("Invokable judge " + name + ": " + "第一个参数是否被定义了注解@Nullable" + annotationPresent );
        }
    }
}
