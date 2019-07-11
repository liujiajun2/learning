package xin.liujiajun.guava.io;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author liujiajun
 * @description Files demo
 * @create 2019-03-14 11:14
 **/
public class FilesDemo {

    public static void main(String[] args) throws IOException {
        File file = new File("file.txt");
        //读取文件内容第一行
        String s = Files.asCharSource(file, Charsets.UTF_8).readFirstLine();
        System.out.println(s);
        //分割文件中的数据
        HashMultiset<String> strings = HashMultiset.create(
                Splitter.on(CharMatcher.whitespace())
                        .trimResults()
                        .omitEmptyStrings()
                        .split(Files.asCharSource(file, Charsets.UTF_8).read()));

        System.out.println(strings);
        //对文件内容 SHA-1运算
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha1());
        System.out.println(hash);

        //从网络上获取数据保存到文件中
        Resources.asByteSource(new URL("http://www.planetb.ca/projects/syntaxHighlighter/popup.php")).copyTo(Files.asByteSink(file));


        String fileExtension = Files.getFileExtension("file.txt.txt");
        //txt
        System.out.println(fileExtension);
        String s1 = Files.simplifyPath("file.txt");
        //file.txt
        System.out.println(s1);

    }
}
