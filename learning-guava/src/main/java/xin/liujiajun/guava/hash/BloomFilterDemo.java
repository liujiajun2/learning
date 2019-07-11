package xin.liujiajun.guava.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.util.ArrayList;

/**
 * @author liujiajun
 * @description BloomFilter demo
 * @create 2019-03-14 10:30
 **/
public class BloomFilterDemo {

    public static void main(String[] args) {
        ArrayList<Person> objects = new ArrayList<>();

        objects.add(new Person(1,"jack","cc",20));
        objects.add(new Person(2,"jack","cc",20));
        objects.add(new Person(3,"jack","cc",20));
        objects.add(new Person(4,"jack","cc",20));
        objects.add(new Person(5,"jack","cc",20));

        Funnel<Person> funnel = new Funnel<Person>() {
            @Override
            public void funnel(Person person, PrimitiveSink into) {
                into.putInt(person.getId())
                        .putString(person.getFirstName(), Charsets.UTF_8)
                        .putString(person.getLastName(), Charsets.UTF_8)
                        .putInt(person.getAge());
            }
        };
        BloomFilter<Person> friends = BloomFilter.create(funnel, 500, 0.01);
        for (Person p : objects) {
            friends.put(p);
        }
        Person person = null;
        for (int i = 0; i < 1000000; i++) {
            person = new Person(i, "jack", "cc", 20);
            if (friends.mightContain(person)){
                System.out.println("id 为" +i+ "  存在");
            }else {
               //
            }
        }
        //output 除了1-5 还有两条输出
        //id 为579534  存在
        //id 为652363  存在


    }
}
