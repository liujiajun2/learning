package xin.liujiajun.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * @author LiuJiaJun
 * @date 2019/5/9 13:49
 */
public class AsynchronousFileChannelDemo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("data/nio/data.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Future<Integer> read = channel.read(buffer, 0);
        while (!read.isDone()){
            buffer.flip();
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            System.out.println(new String(bytes));
            buffer.clear();
        }
    }
}
