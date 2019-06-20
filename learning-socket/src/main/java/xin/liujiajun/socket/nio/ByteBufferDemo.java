package xin.liujiajun.socket.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @author LiuJiaJun
 * @date 2019/6/19 10:51
 */
public class ByteBufferDemo {

    public static void main(String[] args) {
        compact();

    }
    public static void test(){
        //分配
        ByteBuffer buffer = ByteBuffer.allocate(100);
        //直接分配
        ByteBuffer buffer1 = ByteBuffer.allocateDirect(100);
        //包装
        byte[] bytes = "hello".getBytes();
        ByteBuffer buffer2 = ByteBuffer.wrap(bytes);


    }

    public static void compact(){
        CharBuffer buffer = CharBuffer.allocate(100);
        buffer.put("hello");
        buffer.flip();
        //hello
        System.out.println(buffer);
        //0
        System.out.println(buffer.position());
        CharBuffer compact = buffer.compact();
        //5
        System.out.println(compact.position());
    }

}
