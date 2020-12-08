package xin.liujiajun.java.thread;

/**
 * @author liujiajun
 * @date 2020-11-12 10:26
 **/
public class VolatileObj {

    private static boolean flag = false;


    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        VolatileObj.flag = flag;
    }
}
