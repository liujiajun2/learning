package xin.liujiajun.guava.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * @author liujiajun
 * @description demo
 * @create 2019-03-14 10:08
 **/
public class Demo {

    public static void main(String[] args) {

        HashFunction hashFunction = Hashing.md5();
        HashCode hash = hashFunction.newHasher()
                .putLong(124512312L)
                .putString("fdjsafkljdas", Charsets.UTF_8)
                .hash();
        //facb9a2aa4ea0ae4a95326bcd19fe92e
        System.out.println(hash.toString());
        //714787834
        System.out.println(hash.hashCode());

    }
}
