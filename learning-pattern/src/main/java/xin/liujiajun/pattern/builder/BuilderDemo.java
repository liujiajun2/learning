package xin.liujiajun.pattern.builder;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 08:26
 **/
public class BuilderDemo {

    public static void main(String[] args) {
        PersonBuilder person = PersonBuilder.newBuilder().withName("Jack").withAge(1).withGender(1).build();
        System.out.println(person.getAge());
    }
}
