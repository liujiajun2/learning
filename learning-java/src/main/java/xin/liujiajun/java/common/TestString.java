package xin.liujiajun.java.common;

import org.junit.Test;

public class TestString {
    @Test
    public void test(){
        String str1 = "adfasdf";
        String str2 = "abcc";
        System.out.println(str2.length());
        System.out.println(str1.charAt(2));//返回指定index位置的字符
        System.out.println(str1.equals(str2));//比较
        System.out.println(str1.compareTo(str2));//返回不相等的字符相减的值
        System.out.println(str1.indexOf("df"));//首次出现的位置
        System.out.println(str1.lastIndexOf("a"));//最后一次出现的位置
        System.out.println(str1.startsWith("ad"));//什么开始
        System.out.println(str1.endsWith("df"));//什么结束
    }

    @Test
    public void test2(){
        String str1 = "北京老纯风";
        String str2 = "上海大都会";
        String str3 = str1.substring(2,5);
        System.out.println(str3);
        String str4 = str1.replace("北京","南京");
        System.out.println(str4);
        System.out.println("  dsafsd  fsdaf asdf  ".trim());//去空
        System.out.println(str1.concat(str2));//合并
        String str5 = "abc&d-re32-ke";
        String[] strs = str5.split("-");//按某个格式拆分
        for (String str:strs) {
            System.out.print(str);
        }
        System.out.println();
        char [] chars = str1.toCharArray();
        for (char cha: chars) {
            System.out.println(cha);
        }
        String string = new String(chars);
        System.out.println(string);

        String str7 = "1234dsfa";
        byte [] bt = str7.getBytes();
        for (byte b:bt) {
            System.out.println(b);
        }
        System.out.println(new String(bt));
    }
    @Test
    public void test3(){
        //StringBuffer 可变
        StringBuilder sb = new StringBuilder();//可变字符序列，已经初始化16个
        System.out.println(sb.length());
        sb.append("abc").append("456");
        System.out.println(sb);
        sb.insert(3,"hello");
        System.out.println(sb);
        System.out.println(sb.reverse());//反转。本身也改变
        System.out.println(sb);
    }
    @Test
    public void test4(){
        //StringBuilder 线程不安全。效率高于String和StringBuffer
    }}
