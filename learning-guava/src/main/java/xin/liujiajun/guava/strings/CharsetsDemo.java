package xin.liujiajun.guava.strings;

import com.google.common.base.Charsets;

import java.io.UnsupportedEncodingException;

/**
 * @author liujiajun
 * @description Charsets demo
 * @create 2019-03-13 15:36
 **/
public class CharsetsDemo {

    public static void main(String[] args) {
        String str = "test";
        try {
            //不要这样使用
            byte[] bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] bytes = str.getBytes(Charsets.UTF_8);


    }
}
