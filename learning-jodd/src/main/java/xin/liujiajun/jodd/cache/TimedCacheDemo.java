package xin.liujiajun.jodd.cache;

import jodd.cache.TimedCache;

import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-21 14:00
 **/
public class TimedCacheDemo {

    public static void main(String[] args) throws InterruptedException {
        //TimedCache 不限制大小，只有当对象过期时才会删除。
        // 标准的chache方法不会显式的调用删除(prune)，而是根据定义好的延迟进行定时删除。
        TimedCache<String, Integer> cache = new TimedCache<>(1000);

        cache.put("a",1);
        cache.put("b",2);

        TimeUnit.SECONDS.sleep(1);

        //null
        System.out.println(cache.get("a"));
        //null
        System.out.println(cache.get("b"));
    }
}
