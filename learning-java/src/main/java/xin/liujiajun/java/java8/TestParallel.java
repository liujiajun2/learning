package xin.liujiajun.java.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liujiajun
 * @date 2019-10-10 14:00
 **/
public class TestParallel {


    public static void main(String[] args) {

        testMap();
    }

    private static void testSum(){
        ArrayList<Long> list = new ArrayList<>();

        for (long i = 0; i < 100000L; i++) {
            list.add(i);
        }

        long start = System.currentTimeMillis();
        Long sum = list.stream().reduce(0L, Long::sum);

        System.out.println("sum " + sum + " cost time " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        Long sum2 = list.stream().parallel().reduce(0L, Long::sum);
        System.out.println("sum " + sum2 + " cost time " + (System.currentTimeMillis() - start));
    }

    private static void testMap(){
        ArrayList<Person> person = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            person.add(new Person().setName("name" + i));
        }

        long start = System.currentTimeMillis();
        List<String> collect = person.stream().map(Person::getName).collect(Collectors.toList());

        System.out.println("cost time " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        List<String> collect1 = person.stream().map(Person::getName).parallel().collect(Collectors.toList());
        System.out.println("cost time " + (System.currentTimeMillis() - start));
    }
}
