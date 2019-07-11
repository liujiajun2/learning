package xin.liujiajun.guava.io;

import com.google.common.io.ByteSource;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-14 11:37
 **/
public class SourceAndSinkDemo {

    public static void main(String[] args) throws IOException {

        //JDK IO 一般复制文件方式
        FileInputStream fileInputStream = new FileInputStream(new File("file.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("fileCopy.txt"));
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(buff)) != -1) {
            fileOutputStream.write(buff,0,len);
        }
        fileOutputStream.flush();
        fileInputStream.close();
        fileOutputStream.close();

        //实现文件复制 方式1
        ByteSource byteSource = Files.asByteSource(new File("file.txt"));
        long l = byteSource.copyTo(Files.asByteSink(new File("fileCopy.txt"), FileWriteMode.APPEND));

        //实现文件复制 方式2
        FileOutputStream outputStream = new FileOutputStream(new File("fileCopy.txt"));
        Files.copy(new File("file.txt"),outputStream);

//        //转换
//        InputStream inputStream1 = Files.asByteSource(new File("file.txt")).openStream();
//        InputStream inputStream1 = Files.asByteSource(new File("file.txt")).openBufferedStream();
    }
}
