package xin.liujiajun.thread.cocurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author liujiajun
 * @date 2020-09-22 08:45
 **/
public class AtomicIntegerFieldUpdaterDemo {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");
        User user = new User("Bala", 12);

        System.out.println(updater.getAndIncrement(user));
        System.out.println(updater.get(user));
        System.out.println(user.getOld());


    }
    static class User{
        private String name;
        public volatile int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }
}
