package xin.liujiajun.java.java8;

import java.util.concurrent.*;

/**
 * @author liujiajun
 * @date 2020-09-10 20:22
 **/
public class TestCompletionService {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();


        ExecutorCompletionService<Integer> service = new ExecutorCompletionService<>(pool);

        service.submit(()->1);
        service.submit(()->2);
        service.submit(()->3);

        for (int i = 0; i < 3; i++) {
            Future<Integer> future = service.poll();
            if (future == null) {
                break;
            }
            System.out.println(future.get());
        }
    }
}
