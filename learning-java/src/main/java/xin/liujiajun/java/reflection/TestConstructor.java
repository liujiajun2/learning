package xin.liujiajun.java.reflection;

import org.junit.Test;

public class TestConstructor {

    @Test
    public void test() throws Exception{
        String className = "com.liujiajun.reflection.Person";
        Class clazz = Class.forName(className);
        //要想能够创建成功，要求
        // 1.对应的运行时类要有空参的构造器
        //2.构造器权限要足够
        Object object = clazz.newInstance();
        Person p = (Person)object;
        System.out.println(p);
    }
}
