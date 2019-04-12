package xin.liujiajun.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author LiuJiaJun
 * @date 2019/2/28 16:25
 */
public class DemoVolatile {
    private static boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                int i = 0;
                while (DemoVolatile.flag){
                    i++;
                }
                System.out.println(i);
            }
        }.start();

        TimeUnit.SECONDS.sleep(2);
        DemoVolatile.flag = true;
        System.out.println("设置为false");
    }
}
