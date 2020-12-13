package xin.liujiajun.guava.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import java.util.Random;

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

        HashFunction function = Hashing.md5();



        int[] ints = new int[4];
        Random random = new Random();
        int index = 1;
        for (int i = 0; i < 30000; i++) {
            index = index + random.nextInt(100);
            Hasher hasher = function.newHasher();
            int code = hasher.putString(String.valueOf(index), Charsets.UTF_8).hash().hashCode();
            if (code < 0) {
                code = code * -1;
            }
            int rest = code % 4;
            ints[rest]++;
        }

        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

    }

    public int code(Hasher hasher){

        return 0;
    }
}
