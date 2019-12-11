package xin.liujiajun.demo;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liujiajun
 * @date 2019-12-05 17:44
 **/
public class ChannelTest {


    public static void main(String[] args) throws IOException {
        File file = new File("data/nio/data.txt");
        FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
        MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 20);

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put("helloworld".getBytes());

        fileChannel.write(byteBuffer);

        ByteBuffer byteBuffer1 = map.get(new byte[10]);

        CharBuffer charBuffer = map.asCharBuffer();

        char c = charBuffer.get();
        System.out.println(c);
//        fileChannel.force(false);


    }
}
