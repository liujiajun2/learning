package xin.liujiajun.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author LiuJiaJun
 * @date 2019/3/20 22:05
 */
public class MultiListener {

    private Integer intValue;
    private Long longValue;

    @Subscribe
    public void listenInteger(Integer event){
        intValue = event;
        System.out.println("event int " + intValue );
    }

    @Subscribe
    public void listenLong(Long event){
        longValue = event;
        System.out.println("event Long " + longValue);
    }

    public Integer getIntValue(){
        return intValue;
    }

    public Long getLongValue(){
        return longValue;
    }
}
