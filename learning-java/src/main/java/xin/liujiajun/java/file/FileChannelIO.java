package xin.liujiajun.java.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liujiajun
 * @date 2020-04-22 15:02
 **/
public class FileChannelIO {


    public static void main(String[] args) throws IOException {


        long start = System.currentTimeMillis();

        File file = new File("F:\\test.pdf");
//        FileChannel inputChannel = new FileInputStream(file).getChannel();
        FileChannel inputChannel = new RandomAccessFile(file,"rw").getChannel();


        File oldFile = new File("F:\\testNio.pdf");
        FileChannel outputChannel = new FileOutputStream(oldFile).getChannel();


        ByteBuffer buff = ByteBuffer.allocate(32768);

        int len = 0;
        while ((len = inputChannel.read(buff)) > 0) {
            buff.flip();
            outputChannel.write(buff);
            buff.clear();
        }


        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
    }
}
