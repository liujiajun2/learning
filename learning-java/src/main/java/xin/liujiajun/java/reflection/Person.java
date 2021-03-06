package xin.liujiajun.java.reflection;

@myAnnotation(value = "212")
public class Person extends Creature<String> implements Comparable,MyInterface{
    public String name;
    private int age;

    public Person(){
        System.out.println("无参构造器");
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void show(){
        System.out.println("我是一个人");
    }
    public void dispaly(String data) throws Exception{
        System.out.println("展示" + data);
    }

    public int compareTo(Object o) {
        return 0;
    }

    class bird{

    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
