package xin.liujiajun.thread.future;

/**
 * @author LiuJiaJun
 * @date 2019/1/15 19:32
 */
public class Client {
    public Data request(final String queryStr){
        final FutureData future = new FutureData();
        new Thread(){
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        return future;
    }
}
