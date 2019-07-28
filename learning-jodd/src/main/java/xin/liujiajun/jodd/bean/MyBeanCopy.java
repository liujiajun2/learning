package xin.liujiajun.jodd.bean;

import jodd.bean.BeanCopy;
import jodd.bean.BeanUtil;

import java.time.Instant;

/**
 * @author liujiajun
 * @create 2019-04-02 10:49
 **/
public class MyBeanCopy {


    public static void test(){
        Input input = new Input("测试","会议室1",1);
        Plan plan = new Plan();
        BeanCopy.from(input).to(plan).copy();
        System.out.println(input);
        System.out.println(plan);

        System.out.println(Instant.now().getEpochSecond());
    }

    public static void main(String[] args) {
//        test();
        testAdd();
    }


    public static void testAdd(){
        Input input = new Input();
//        BeanUtil.pojo.setIndexProperty(input,"test","fdasfadfasdf");
    }
}
