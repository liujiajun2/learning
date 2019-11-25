package xin.liujiajun.thread.context;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2019-11-16 11:19
 **/
public class ContextSwap {

    public static void main(String[] args) {
        MuitiWork muitiWork = new MuitiWork();
        SingleWork singleWork = new SingleWork(muitiWork);
        SingleWork singleWork2 = new SingleWork(muitiWork);

        new Thread(singleWork).start();
        new Thread(singleWork2).start();
    }

    private static class SingleWork implements Runnable{

        private MuitiWork muitiWork;

        public SingleWork(MuitiWork muitiWork) {
            this.muitiWork = muitiWork;
        }

        @Override
        public void run() {
            try {
                long start = System.currentTimeMillis();
                for (int i = 0; i < 1000; i++) {
                    TimeUnit.MILLISECONDS.sleep(20);
                    muitiWork.work();
                }
                System.out.println(System.currentTimeMillis() - start + "ms");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MuitiWork{
        private ExecutorService executorService = Executors.newFixedThreadPool(8);

        public void work(){
            ArrayList<String> list = new ArrayList<>();
            list.add("111");
            list.parallelStream().forEach(s->{
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
