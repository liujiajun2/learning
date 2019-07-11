package xin.liujiajun.pattern.strategy;

/**
 * @author liujiajun
 * @description 后门
 * @create 2019-03-11 15:06
 **/
public class BackDoor implements IStrategy{
    @Override
    public void operate() {
        System.out.println("找别人帮忙，给孙权施压");
    }
}
