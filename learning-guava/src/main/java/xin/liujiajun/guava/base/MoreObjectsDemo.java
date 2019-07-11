package xin.liujiajun.guava.base;

import com.google.common.base.MoreObjects;

/**
 * @author liujiajun
 * @description MoreObjects demo
 * @create 2019-03-11 17:37
 **/
public class MoreObjectsDemo {
    private int a;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("a",this.a)
                .add("x", 1).toString();
    }

    public static void main(String[] args) {
        //MoreObjectsDemo{a=0, x=1}
        System.out.println(new MoreObjectsDemo());
    }
}
