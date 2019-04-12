package xin.liujiajun.java.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

public class TestGson {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Student student = new Student();
        student.setName("张三");
        student.setAge(20);
        //javaBean转Json字符串
        String jsonStr = gson.toJson(student);
        System.out.println(jsonStr);
        //List 转json字符串
        List<String> list = Arrays.asList("1","a","b","v");
        System.out.println(gson.toJson(list));
        //Map转json字符串
        Map<String,Object> content = new HashMap<String,Object>();
        content.put("name","lisi");
        content.put("age","35");
        System.out.println(gson.toJson(content));
        //Json字符串转JavaBean
        String studentStr = "{\"name\":\"xuanyouwu\",\"age\":26}";
        Student student1 = gson.fromJson(studentStr,Student.class);
        System.out.println(student1);
        //Json字符串转List
        String listJsonStr="[\"1\",\"a\",\"3\",\"rt\",\"5\"]";
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> sList = gson.fromJson(listJsonStr,type);
        System.out.println(sList);
    }
    public void test1(){
        Gson gson = new Gson();
        gson = new GsonBuilder()
                .setLenient()//json宽松
                .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                .serializeNulls()//智能null
                .setPrettyPrinting()//调教格式
                .disableHtmlEscaping()//默认是GSON把HTML 转义的
                .create();
    }

}
class Student{
    private String name;
    private int age;

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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


