package xin.liujiajun.thread.future;

/**
 * @author LiuJiaJun
 * @date 2019/1/15 19:37
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        Data name = client.request("name");
        System.out.println("请求完毕");
        System.out.println("数据 =" + name.getResult());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据 =" + name.getResult());
    }
}
