package xin.liujiajun.mybatis.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiuJiaJun
 * @date 2018/10/9 16:33
 */
public class ObjectTest {


    public static void main(String[] args) {
        /**
         * 多态，简单说--->父类型的变量可以引用子类型的对象
         * 存在动态绑定。方法可以在沿着继承链的多个类中实现。JVM决定运行时调用哪个方法
         * 对象转换。子类转父类，隐式，向上转换。父类转子类，强制，向下转换
         * 一个好的经验，是把变量定义为父类型，这样，它就可以接受任何子类型的值。
         */
        Object o = new ObjectTest();
        ((ObjectTest) o).say();
        List<String> arr = new ArrayList<>();
       ((ArrayList<String>) arr).trimToSize();
    }

    public void say(){
        System.out.println("hello");
    }
}
