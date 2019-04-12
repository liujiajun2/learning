package xin.liujiajun.java.thread;

class Clerk{
    int product;
    public synchronized void addProduct(){
        if( product >=20){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            product++;
            System.out.println(Thread.currentThread().getName()+"生产了第"+product+"产品");
            notifyAll();
        }
    }
    public synchronized void consume(){
        if( product <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(Thread.currentThread().getName()+"消费了第"+product+"产品");
            product--;
            notifyAll();
        }
    }
}
class Producer implements Runnable{
    Clerk clerk;
    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }
    public void run(){
        System.out.println("生产者开始生产产品");
        while (true){
            try {
                Thread.currentThread().sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.addProduct();
        }
    }
}
class Custom implements Runnable{
    Clerk clerk;
    public Custom(Clerk clerk) {
        this.clerk = clerk;
    }
    public void run(){
        System.out.println("消费者开始消费产品");
        while (true){
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consume();
        }
    }
}
/**
 * 生产者、消费者问题
 */
public class TestConsume {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p = new Producer(clerk);
        Custom c = new Custom(clerk);

        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);
        Thread t3 = new Thread(p);

        t1.setName("生产者1");
        t2.setName("消费者1");
        t3.setName("生产者2");

        t1.start();
        t2.start();
        t3.start();
    }
}
