package xin.liujiajun.guava.reflection;

import com.google.common.reflect.Reflection;

/**
 * @author liujiajun
 * @description ClassLoading demo
 * @create 2019-03-14 09:17
 **/
public class ClassLoadingDemo {


    public static void main(String[] args) {
        //OK是编译期静态常量，不会触发类初始化，所以只会输出ok
        System.out.println(Constant.OK);
        //这里会输出 static init
        Reflection.initialize(Constant.class);
    }
}
