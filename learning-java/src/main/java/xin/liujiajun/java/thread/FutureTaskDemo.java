package xin.liujiajun.java.thread;

import java.util.concurrent.*;

/**
 * @author liujiajun
 * @date 2020-09-07 09:05
 **/
public class FutureTaskDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        executor();

//        thread();

        run();

    }


    public static void executor() throws ExecutionException, InterruptedException {
        //public FutureTask(Callable<V> callable)
        FutureTask<Integer> task = new FutureTask<>(() -> 1 + 2);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        //Future<?> submit(Runnable task)
        fixedThreadPool.submit(task);
        System.out.println(task.get());
    }

    public static void thread() throws ExecutionException, InterruptedException {
        Result result = new Result().setValue(2);
        AddTask addTask = new AddTask(result);
        //public FutureTask(Runnable runnable, V result)
        FutureTask<Result> task = new FutureTask<>(addTask, result);

        Thread thread = new Thread(task);
        thread.start();

        System.out.println(task.get());


    }

    static class AddTask implements Runnable{
        private Result result;

        public AddTask(Result value) {
            this.result = value;
        }

        @Override
        public void run() {
            result.setValue(result.getValue() + 1);
        }
    }
    static class Result{
        private Integer value;

        public Integer getValue() {
            return value;
        }

        public Result setValue(Integer value) {
            this.value = value;
            return this;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "value=" + value +
                    '}';
        }
    }

    public static void run() throws ExecutionException, InterruptedException {
        //public FutureTask(Callable<V> callable)
        FutureTask<Integer> task = new FutureTask<>(() -> 1 + 2);
        //Future<?> submit(Runnable task)
        task.run();
        System.out.println(task.get());
    }
}
