package xin.liujiajun.java.batchmessage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2019-12-23 14:33
 **/
public class Test {

    public static void main(String[] args) {
        BatchMessageService putMessageService = new TestPutMessageService();
        putMessageService.start();

        ExecutorService ex = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10000; i++) {
            if (i == 501) {
               sleep();
            }
            int finalI = i;
            ex.submit(()-> putMessageService.putMessage(String.valueOf(finalI)));


        }
    }
    private static void sleep(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
