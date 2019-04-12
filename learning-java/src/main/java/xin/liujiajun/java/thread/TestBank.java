package xin.liujiajun.java.thread;

public class TestBank {
    public static void main(String[] args) {
        //“实现”的方式，实现两个用户存储同一个账号的钱
        Bank bank = new Bank();
        Thread t1 = new Thread(bank);
        Thread t2 = new Thread(bank);

        t1.setName("甲");
        t2.setName("乙");

        t1.start();
        t2.start();

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //“继承”的方式，实现两个用户存储同一个账号的钱
        Account account = new Account();
        Customer customer1 = new Customer(account);
        Customer customer2 = new Customer(account);
        customer1.setName("第一个用户");
        customer2.setName("第二个用户");
        customer1.start();
        customer2.start();

    }
}
class Bank implements Runnable{
    private int money = 0;

    public void run() {
        for (int i = 0; i < 3; i++) {
            synchronized (this) {
                money += 1000;
                System.out.println(Thread.currentThread().getName() + ":" + money);
            }
        }
    }
}
class Account{
    private int money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
class Customer extends Thread{
    private Account account;
    public Customer(Account account){
        this.account = account;
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            synchronized (account) {
                account.setMoney(account.getMoney() + 1000);
                System.out.println(Thread.currentThread().getName() + ":" + account.getMoney());
            }
        }
    }
}
