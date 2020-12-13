package xin.liujiajun.http.async;

import com.ning.http.client.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author LiuJiaJun
 * @date 2020/7/11 11:13
 */
public class AsyncDemo {


    public static void main(String[] args) throws FileNotFoundException {

        AsyncHttpClient httpClient = new  AsyncHttpClient();
        File file = new File("J://test.tar.gz");
        FileOutputStream outputStream = new FileOutputStream(file);
        BufferedOutputStream buffer = new BufferedOutputStream(outputStream,64*1024);
//        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594458650851&di=d2cbb56672e26982616274d7fd063456&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D1484500186%2C1503043093%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D853";
        String url = "https://mirrors.bfsu.edu.cn/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz";
        long start = System.currentTimeMillis();
        httpClient.prepareGet(url).execute(new AsyncCompletionHandler<Object>() {

            private long total;
            private long current = 0;

            @Override
            public Object onCompleted(Response response) throws Exception {
                outputStream.close();
                System.out.println("cost time " + (System.currentTimeMillis() - start));
                return null;
            }

            @Override
            public STATE onBodyPartReceived(HttpResponseBodyPart content) throws Exception {
                current += content.length();

                System.out.println("process curr/total： "+current+"/"+total);
//                outputStream.getChannel().write(content.getBodyByteBuffer());
                content.writeTo(buffer);
                return super.onBodyPartReceived(content);

            }

            @Override
            public STATE onHeadersReceived(HttpResponseHeaders headers) throws Exception {
                List<String> length = headers.getHeaders().get("Content-Length");
                for (String len : length){
                    total = Long.parseLong(len);
                }
                return super.onHeadersReceived(headers);
            }


        });



    }
}
