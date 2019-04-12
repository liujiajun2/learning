package xin.liujiajun.java.reflection;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class TestReflection {

    @Test
    public void test1(){
        //在反射以前，如何创建一个类的对象
        Person p = new Person();
        p.show();
    }
    @Test
    public void test2() throws Exception{
        Class<Person> clazz = Person.class;
        //创建clazz对应的运行时类的对象
        Person p = clazz.newInstance();
        System.out.println(p);
        //反射调用属性
        Field f1 = clazz.getDeclaredField("name");
        f1.setAccessible(true);
        f1.set(p,"zhangsan");
        System.out.println(p);
        //反射调用方法
        Method m1 = clazz.getMethod("show");
        m1.invoke(p);

        Method m2 = clazz.getMethod("dispaly", String.class);
        m2.invoke(p,"数据");
    }

    @Test
    public void test3(){

        Person p = new Person();
        Class clazz = p.getClass();//通过运行时类的对象，调用其getClass,返回其运行时类
        System.out.println(clazz);
    }
    /**
     * java.lang.Class：是反射源头
     * 创建一个雷，通过编译（javac.exe）生成对应的class文件
     * 使用java.exe加载（JVM的类加载器完成）
     * 此.class文件加载到内存以后，就是一个运行时类，存在缓存区
     * 这个运行时类本身就是一个Class的实例
     * 1.每个运行时类只加载一次
     * 2.1）创建对应的运行时类的对象；
     *   2）获取对应运行时类的完整结构（属性，方法。。。）
     *   3）调用对应额运行时泪的结构（属性，方法，构造器）
     *   4）反射的运用，动态代理
     */
    @Test
    public void test4(){
        /**
         * 如何获取Class实例（3种）
         *
         */
        //1.调用运行时本身的.class属性
        Class clazz1 = Person.class;
        System.out.println(clazz1.getName());

        Class clazz2 = String.class;
        System.out.println(clazz2.getName());

        //2.通过运行时类的对象获取
        Person p = new Person();
        Class clazz3 = p.getClass();
        System.out.println(clazz3.getName());

        //3.通过Class的静态方法获取
        String className = "com.liujiajun.reflection.Person";
        try {
            Class clazz4 = Class.forName(className);
            System.out.println(clazz4.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //4.通过类的加载器(了解)
        ClassLoader loader = this.getClass().getClassLoader();
        try {
            Class clazz5 = loader.loadClass(className);
            System.out.println(clazz5);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5(){
        /**
         * 类加载器
         */
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        System.out.println(loader);

        ClassLoader loader1 = loader.getParent();
        System.out.println(loader1);

        ClassLoader loader2 = loader1.getParent();
        System.out.println(loader2);

        Class clazz = Person.class;
        ClassLoader loader3 = clazz.getClassLoader();
        System.out.println(loader3);

        String className = "java.lang.Object";
        try {
            Class clazz2 = Class.forName(className);
            ClassLoader loader4 = clazz2.getClassLoader();
            System.out.println(loader4);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //掌握如下
        //方法一，在包里直接用
//        ClassLoader loader4 = this.getClass().getClassLoader();
//        InputStream inputStream = loader4.getResourceAsStream("com\\liujiajun\\reflection\\text.properties");
//        Properties properties = new Properties();
//        try {
//            properties.load(inputStream);
//            String name = properties.getProperty("name");
//            System.out.println(name);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //方法二，根目录下
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("text.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            String name = properties.getProperty("name");
            System.out.println(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
