package xin.liujiajun.jodd.cache;


import jodd.cache.LRUCache;

import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 13:55
 **/
public class LRUCacheDemo {

    public static void main(String[] args) throws InterruptedException {

        // LRUCache：最近未访问缓存。缓存对象的消耗是一个常量。简单高效，比FIFO更适应一个变化的场景。
        // 缺点是可能会被不会重新访问的缓存占满空间，特别是在面对获取类型扫描时则完全不起作用。然后它是目前最常用的缓存算法。
        LRUCache<String, Integer> cache = new LRUCache<>(2);

        cache.put("a",1,1000);
        //sleep 1 秒
        TimeUnit.SECONDS.sleep(1);

        // null
        System.out.println(cache.get("a"));

        cache.put("b",2);
        // 2
        System.out.println(cache.get("b"));


    }
}
