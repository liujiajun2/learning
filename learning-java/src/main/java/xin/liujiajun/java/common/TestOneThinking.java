package xin.liujiajun.java.common;

/**
 * 这个类用来测试一种验证，一般重载可以通过类型来判断应该调用哪个方法，
 * 那么如果是两个都是对象类型，传入null的时候会调用哪个类
 * 猜测是：根据方法顺序来
 * 验证是：Ambiguous method call. Both
 * 好吧，都不是，编译器直接报错了
 *
 * @author LiuJiaJun
 * @date 2019/2/25 14:11
 */
public class TestOneThinking {


    public void testThink(Integer integer){
        System.out.println("输出整数");
    }

    public void testThink(Double d){
        System.out.println("输出浮点数");
    }

    public static void main(String[] args) {
//        new TestOneThinking().testThink(1);
    }
}
