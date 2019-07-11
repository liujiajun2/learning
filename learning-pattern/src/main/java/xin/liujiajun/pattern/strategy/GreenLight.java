package xin.liujiajun.pattern.strategy;

/**
 * @author liujiajun
 * @description 吴国太开个绿灯
 * @create 2019-03-11 15:08
 **/
public class GreenLight implements IStrategy {
    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯，放行");
    }
}
