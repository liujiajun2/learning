package xin.liujiajun.jodd.cache;

import jodd.cache.FileLFUCache;

import java.io.File;
import java.io.IOException;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 14:30
 **/
public class FileCacheDemo {

    public static void main(String[] args) throws IOException {
        FileLFUCache cache = new FileLFUCache(2, 10 * 1024 * 1024);

        cache.getFileBytes(new File("pom.xml"));
        //使用的存储内容
        System.out.println(cache.usedSize());
    }
}
