package xin.liujiajun.java.batchmessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author liujiajun
 * @date 2019-12-23 13:52
 **/
public abstract class BatchMessageService<T> implements Runnable{

    protected static final Logger logger = LoggerFactory.getLogger(BatchMessageService.class);

    /**
     * 批量处理的数据大小
     */
    private static final int BATCH_SIZE = 200;
    /**
     * 定时处理批量数据，每10s，处理一次
     */
    private static final long INTERVAL = 10000;
    /**
     * 等待线程终止时间
     */
    private static final long JOIN_TIME = 90 * 1000;

    private volatile List<T> requestWrite = new ArrayList<>();
    private volatile List<T> requestRead = new ArrayList<>();
    private volatile boolean stopped = false;

    private Thread thread;
    private final AtomicBoolean started = new AtomicBoolean(false);

    protected final CountDownLatch2 waitPoint = new CountDownLatch2(1);
    protected volatile AtomicBoolean hasNotified = new AtomicBoolean(false);

    public synchronized void putMessage(T t){
        synchronized (this.requestWrite) {
            this.requestWrite.add(t);
            if (this.requestWrite.size() >= getBatchSize()) {
                if (hasNotified.compareAndSet(false,true)){
                    waitPoint.countDown();
                }
            }
        }
    }

    public synchronized void putBatchMessage(List<T> list){
        synchronized (this.requestWrite) {
            this.requestWrite.addAll(list);
            if (this.requestWrite.size() >= getBatchSize()) {
                if (hasNotified.compareAndSet(false,true)){
                    waitPoint.countDown();
                }
            }
        }
    }

    public void start(){
        if (!started.compareAndSet(false, true)) {
            return;
        }
        stopped = false;
        this.thread = new Thread(this, this.getServiceName());
        this.thread.setDaemon(false);
        this.thread.start();
    }

    private void swapRequests(){
        List<T> tmp = this.requestWrite;
        this.requestWrite = this.requestRead;
        this.requestRead = tmp;

    }

    private void doCommit(){
        synchronized (this.requestRead) {
            if (!this.requestRead.isEmpty()) {
                boolean isContinue = true;
                while (isContinue) {
                    List<T> commitList;
                    int size = this.requestRead.size();
                    int batchSize = getBatchSize();
                    if (size > batchSize) {
                        //subList返回一个List视图，修改和其他操作都会影响原数据
                        List<T> tmpList = this.requestRead.subList(0, batchSize);
                        commitList = new ArrayList<>(tmpList);
                        tmpList.clear();
                    }else{
                        List<T> tmpList = this.requestRead.subList(0,this.requestRead.size());
                        commitList = new ArrayList<>(tmpList);
                        tmpList.clear();
                        isContinue = false;
                    }
                    doBatchMessage(commitList);
                }
            }
        }
    }

    @Override
    public void run() {
        logger.info(this.getServiceName() + " service started ");
        while (!this.isStopped()) {
            try{
                this.waitForRunning(getInterval());
                this.doCommit();
            }catch (Exception e) {
                logger.error(this.getServiceName() + "service has exception , " ,e);
            }
        }
    }

    private void waitForRunning(long interval) {
        if (hasNotified.compareAndSet(true, false)) {
            this.onWaitEnd();
            return;
        }

        //entry to wait
        waitPoint.reset();

        try {
            waitPoint.await(interval, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("Interrupted : " + e);
        } finally {
            hasNotified.set(false);
            this.onWaitEnd();
        }
    }

    private void onWaitEnd(){
        //交换数据
        this.swapRequests();
    }

    public void shutdown() {
        this.shutdown(false);
    }

    public void shutdown(final boolean interrupt) {
        logger.info("Try to shutdown service thread:{} started:{} lastThread:{}", getServiceName(), started.get(), thread);
        if (!started.compareAndSet(true, false)) {
            return;
        }
        this.stopped = true;
        logger.info("shutdown thread " + this.getServiceName() + " interrupt " + interrupt);

        if (hasNotified.compareAndSet(false, true)) {
            waitPoint.countDown(); // notify
        }

        try {
            if (interrupt) {
                this.thread.interrupt();
            }

            long beginTime = System.currentTimeMillis();
            if (!this.thread.isDaemon()) {
                this.thread.join(this.getJointime());
            }
            long eclipseTime = System.currentTimeMillis() - beginTime;
            logger.info("join thread " + this.getServiceName() + " eclipse time(ms) " + eclipseTime + " "
                    + this.getJointime());
        } catch (InterruptedException e) {
            logger.error("Interrupted", e);
        }
    }

    private boolean isStopped() {
        return stopped;
    }

    protected long getJointime(){
        return JOIN_TIME;
    }

    protected long getInterval(){
        return INTERVAL;
    }

    protected int getBatchSize(){
        return BATCH_SIZE;
    }

    protected String getServiceName(){
        return BatchMessageService.class.getSimpleName();
    }

    /**
     * 批量处理消息
     *
     * @param messages
     */
    protected abstract void doBatchMessage(List<T> messages);
}
