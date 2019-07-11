package xin.liujiajun.guava.base;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-19 17:38
 **/
public class PreconditionsTest {



    @Test
    public void test(){

        Preconditions.checkArgument(1 > 2 ,"参数错误");
        Preconditions.checkNotNull(null);
    }
}



