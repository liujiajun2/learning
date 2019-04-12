package xin.liujiajun.java.reflection;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestMethod {
    @Test
    public void test(){
        Class clazz = Person.class;
        //获取运行时类及其父类的public方法
        Method [] m1 = clazz.getMethods();
        for (Method m:m1) {
            System.out.println(m);
        }
        //获取运行时类的所有方法
        Method [] m2 = clazz.getDeclaredMethods();
        for (Method m:m2) {
            System.out.println(m);
        }
    }
    @Test
    public void test2(){
        Class clazz = Person.class;
        Method [] m2 = clazz.getDeclaredMethods();
        for (Method m:m2) {
//            System.out.println(m);
            //1.注解
            Annotation [] ann = m.getAnnotations();
            for (Annotation annotation : ann){
                System.out.println(annotation);
            }
            //2.权限修饰符
                String str = Modifier.toString(m.getModifiers());
            System.out.print(str+" ");
            //3.返回值类型
            Class returnType = m.getReturnType();
            System.out.print(returnType.getName() + " ");
            //4.方法名
            System.out.print(m.getName());
            //5.形参列表
            System.out.print("(");
            Class[] param = m.getParameterTypes();
            for (int i =0; i<param.length; i++){
                System.out.print(param[i].getName() + "args" + i + ",");
            }
            System.out.print(")");
            //6.异常类型
            Class [] exp = m.getExceptionTypes();
            for (int i =0; i<exp.length; i++) {
                System.out.print(exp[i].getName());
            }
            System.out.println();
        }
    }
}
