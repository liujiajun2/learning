package xin.liujiajun.guava.io;

import com.google.common.io.ByteStreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author liujiajun
 * @description ByteAndCharStreams demo
 * @create 2019-03-14 11:57
 **/
public class ByteAndCharStreamsDemo {

    public static void main(String[] args) throws IOException {

        //实现文件的复制
        FileInputStream inputStream = new FileInputStream(new File("file.txt"));
        ByteStreams.copy(inputStream,new FileOutputStream(new File("fileCopy.txt")));
    }
}
