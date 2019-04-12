package xin.liujiajun.java.json;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestJsonObject{

    public static void main(String[] args) {
        TestJsonObject t = new TestJsonObject();
        t.test();
        t.test2();
        t.test3();
        t.test4();
    }

    /**
     * 直接构建一个json字符串
     */
    public void test(){
        JSONObject object = new JSONObject();
        object.put("张三",26);
        object.put("lisi",27);
        object.put("wangwu",66);
        object.put("haha",new String [] {"heihei","xixi"});
        System.out.println(object.toString());

    }

    /**
     * Map构造出JSON字符串
     */
    public void test2(){
        Map<String,Object> map = new  HashMap<String,Object>();
        map.put("name","Jonh");
        map.put("sex","male");
        map.put("age",22);
        JSONObject object = new JSONObject(map);
        System.out.println(object.toString());
    }

    /**
     * javaBean构造，是有一个空字符串
     */
    public void test3(){
        PersonInfo info = new PersonInfo();
        info.setName("张三");
        info.setSex("male");
        info.setAge(20);

        JSONObject object = new JSONObject(info);
        System.out.println(object.toString());
    }

    /**
     * 将json格式转为javaBean
     */
    public void test4(){
        File file = new File("src/main/resources/data.json");
        try {
            String content = FileUtils.readFileToString(file);
            JSONObject object = new JSONObject(content);
            System.out.println("name="+object.getString("name"));
            System.out.println("sex="+object.getString("sex"));
            System.out.println("age="+object.getInt("age"));
            JSONArray hobby = object.getJSONArray("hobby");
            System.out.println("hobby:");
            for (int i = 0; i <hobby.length() ; i++) {
                System.out.println(hobby.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class PersonInfo{
    private String name;
    private String sex;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}