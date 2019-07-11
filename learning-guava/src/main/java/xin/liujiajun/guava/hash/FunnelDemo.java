package xin.liujiajun.guava.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.*;

/**
 * @author liujiajun
 * @description Funnel demo
 * @create 2019-03-14 10:23
 **/
public class FunnelDemo {

    public static void main(String[] args) {
        Funnel<Person> funnel = new Funnel<Person>() {
            @Override
            public void funnel(Person person, PrimitiveSink into) {
                into.putInt(person.getId())
                        .putString(person.getFirstName(), Charsets.UTF_8)
                        .putString(person.getLastName(), Charsets.UTF_8)
                        .putInt(person.getAge());
            }
        };
        HashFunction hashFunction = Hashing.md5();
        HashCode hash = hashFunction.newHasher()
                .putObject(new Person(1,"jack","lam",20), funnel)
                .hash();
        //9bd9a7155e98d6dbd3ade93d3d35f79b
        System.out.println(hash.toString());
        //363321755
        System.out.println(hash.hashCode());
    }
}
