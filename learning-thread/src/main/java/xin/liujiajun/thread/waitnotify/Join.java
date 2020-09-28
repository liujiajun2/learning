package xin.liujiajun.thread.waitnotify;

/**
 * @author liujiajun
 * @date 2020-08-12 19:34
 **/
public class Join {


    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(()->{
            System.out.println("tttt");
        });

        thread.start();

        thread.join();

        System.out.println("end.....");

    }
}
