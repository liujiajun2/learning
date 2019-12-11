package xin.liujiajun.demo;

import java.nio.ByteBuffer;

/**
 * @author liujiajun
 * @date 2019-12-06 10:51
 **/
public class AllocateDirectDemo {

    public static void main(String[] args) {
        int fileSize = 100 * 1024 * 1024 * 1024;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(fileSize);
    }
}
