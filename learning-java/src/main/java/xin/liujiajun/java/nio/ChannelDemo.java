package xin.liujiajun.java.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author LiuJiaJun
 * @date 2019/5/9 12:08
 */
public class ChannelDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio/data.txt", "rw");
        FileChannel channel = aFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = channel.read(buffer);
        if (bytesRead != -1) {
            System.out.println("read:" + bytesRead);
            //buffer翻转，从头开始读
            //flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。
            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.println((char)buffer.get());
            }
        }
        buffer.clear();
    }
}
