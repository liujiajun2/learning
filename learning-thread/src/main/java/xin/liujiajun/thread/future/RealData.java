package xin.liujiajun.thread.future;

/**
 * @author LiuJiaJun
 * @date 2019/1/15 19:31
 */
public class RealData implements Data {
    protected final String result;
    public RealData(String param){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            buffer.append(param);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = buffer.toString();
    }
    @Override
    public String getResult() {
        return result;
    }
}
