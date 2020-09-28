package xin.liujiajun.java.file;

import java.io.*;

/**
 * @author liujiajun
 * @date 2020-04-22 15:02
 **/
public class BufferedIO {

    public static void main(String[] args) throws IOException {


        long start = System.currentTimeMillis();

        File file = new File("F:\\test.pdf");
        BufferedInputStream buffin = new BufferedInputStream(new FileInputStream(file));

        File oldFile = new File("F:\\testOio.pdf");
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(oldFile));

        int len = 0;
        byte[] bytes = new byte[32768];
        while ((len = buffin.read(bytes)) > 0) {
            output.write(bytes,0,len);
        }


        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");

    }
}
