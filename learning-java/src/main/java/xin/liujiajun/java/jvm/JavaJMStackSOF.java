package xin.liujiajun.java.jvm;

/**
 * @author liujiajun
 * @date 2020-11-12 20:27
 **/
public class JavaJMStackSOF {


    private int stackLen = 1;

    public void stackLeak(){
        stackLen ++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaJMStackSOF obj = new JavaJMStackSOF();
        try {
            obj.stackLeak();
        }catch (Throwable e) {
            System.out.println("stack length:" + obj.stackLen);
            throw e;
        }
    }
}
