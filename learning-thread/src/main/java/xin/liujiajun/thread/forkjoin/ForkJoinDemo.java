package xin.liujiajun.thread.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author liujiajun
 * @date 2020-09-28 08:43
 **/
public class ForkJoinDemo {


    static class CountTask extends RecursiveTask<Integer> {
        private static final int THRESHOLD = 2;
        private int start;
        private int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            boolean canCompute = (end - start) <= THRESHOLD;
            //任务比较小的话，就进行计算
            if (canCompute) {
                for (int i = start; i <= end; i++) {
                    sum +=i;
                }
            }else{
                //如果任务大于阈值，就分裂成两个子任务计算
                int middle = (start + end)/ 2;
                CountTask leftTask = new CountTask(start, middle);
                CountTask rightTask = new CountTask(middle + 1, end);
                //执行子任务
                leftTask.fork();
                rightTask.fork();
                //等待子任务执行完，并获取结果
                Integer leftResult = leftTask.join();
                Integer rightResult = rightTask.join();

                sum = leftResult + rightResult;
            }
            return sum;
        }
    }

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1, 4);

        if (task.isCompletedAbnormally()){
            Throwable exception = task.getException();
        }

        ForkJoinTask<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
