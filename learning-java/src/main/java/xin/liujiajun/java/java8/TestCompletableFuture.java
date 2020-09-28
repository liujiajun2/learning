package xin.liujiajun.java.java8;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author liujiajun
 * @date 2020-09-01 09:06
 **/
public class TestCompletableFuture {

    public static void main(String[] args) {

        either();

    }

    public static void serial(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(t -> t + " !");

        System.out.println(future.join());
    }

    public static void either(){
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int t = random.nextInt(1, 5);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f1->" + t;
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int t = random.nextInt(1, 5);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f2->" + t;
        });

        CompletableFuture<String> f3 = f1.applyToEither(f2, s -> s);
        try {
            System.out.println(f3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
