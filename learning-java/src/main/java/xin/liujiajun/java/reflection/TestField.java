package xin.liujiajun.java.reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TestField {
    @Test
    public void test1(){
        Class clazz = Person.class;

        //1.只能获取声明为public的属性
        Field[] field = clazz.getFields();
        for (int i = 0 ; i <field.length;i++){
            System.out.println(field[i]);
        }

        //2.运行声明时类本身的所有属性
        Field[] field1 = clazz.getDeclaredFields();
        for (Field f:field1) {
            System.out.println(f);
        }
    }
    @Test
    public void test2(){
        Class clazz = Person.class;
        //2.运行声明时类本身的所有属性
        Field[] field1 = clazz.getDeclaredFields();
        for (Field f : field1) {
            System.out.println(f);
            //获取每个属性的权限修饰符
            int i = f.getModifiers();
            String str = Modifier.toString(i);
            System.out.println(str);
            //获取属性的变量类型
            Class type = f.getType();
            System.out.println(type.getName());
//           属性名
            System.out.println(f.getName());
        }

    }
}
