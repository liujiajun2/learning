package xin.liujiajun.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author LiuJiaJun
 * @date 2019/5/9 12:46
 */
public class ChannelDemo2 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("data/nio/from.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("data/nio/to.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel,position,count);
        fromChannel.transferTo(position,count,toChannel);
    }
}
