package xin.liujiajun.java.jmx;

/**
 * @author liujiajun
 * @date 2019-12-11 09:08
 **/
public class Hello implements HelloMBean {

    private String name;
    private String age;
    @Override
    public String getName() {
        System.out.println("get name");
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAge() {
        System.out.println("get age");
        return this.age;
    }

    @Override
    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public void helloWorld() {
        System.out.println("hello world");
    }

    @Override
    public void helloWorld(String str) {
        System.out.println("hello " + str);
    }

    @Override
    public void getTelephone() {
        System.out.println("get telephone");
    }
}
