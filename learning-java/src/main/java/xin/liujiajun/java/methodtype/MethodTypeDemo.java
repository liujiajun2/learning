package xin.liujiajun.java.methodtype;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author LiuJiaJun
 * @date 2019/5/12 12:33
 */
public class MethodTypeDemo {

    public static void main(String[] args) {

        MethodType type = MethodType.methodType(String.class);
        System.out.println(type.toString());

        testInvoke();
    }

    public static void testInvoke(){
        Object revr = "a";
        MethodHandle mh = null;
        try {
            MethodType methodType = MethodType.methodType(int.class);
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            mh = lookup.findVirtual(revr.getClass(), "hashCode", methodType);
            int ret;
            try {
                ret = (int)mh.invoke(revr);
                System.out.println(ret);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
