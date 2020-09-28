package xin.liujiajun.thread.cocurrency;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author liujiajun
 * @date 2020-09-21 20:09
 **/
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicReference<User> reference = new AtomicReference<>();

        User user = new User("Michael", 15);
        reference.set(user);
        User newUser = new User("Jack", 18);

        reference.compareAndSet(user,newUser);

        System.out.println(reference.get().getName());
        System.out.println(reference.get().getOld());

        AtomicMarkableReference<User> markableReference = new AtomicMarkableReference<User>(user,false);
        markableReference.compareAndSet(user,newUser,false,true);

        System.out.println(markableReference.getReference().getName());

        AtomicReferenceFieldUpdater<User, String> updater = AtomicReferenceFieldUpdater.newUpdater(User.class, String.class, "name");
        updater.set(user,"ttt");
        System.out.println(updater.get(user));
    }

    static class User{
         volatile String name;
        public int old;

        public User() {
        }

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
