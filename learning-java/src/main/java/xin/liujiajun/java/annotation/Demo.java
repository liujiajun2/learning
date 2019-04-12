package xin.liujiajun.java.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * 注解：JDK提供常用的注解
 * 1.@Override
 * 2.Deprecated
 * 3.SuppressWarnings
 *
 */
public class Demo {
    public static void main(String[] args) {
        Person p = new Student();
        p.walk();
        p.eat();
        List list = new ArrayList();
    }
}
class Student extends Person{
    public void walk(){
        System.out.println(333333);
    }
    @Override
    public void eat(){
        System.out.println(444444);
    }
}
class Person{
    private String name;
    private int age;
    public Person(){

    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void walk(){
        System.out.println(11111);
    }
    @Deprecated
    public void eat(){
        System.out.println(22222);
    }
}
