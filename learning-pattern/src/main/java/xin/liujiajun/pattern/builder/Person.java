package xin.liujiajun.pattern.builder;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 08:17
 **/
public class Person {

    private String name;
    private Integer age;
    private Integer gender;

    public Person(){
        this("");
    }

    public Person(String name){
        this(name,0);
    }
    public Person(String name,Integer age){
        this(name,age,0);
    }

    public Person(String name,Integer age,Integer gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
