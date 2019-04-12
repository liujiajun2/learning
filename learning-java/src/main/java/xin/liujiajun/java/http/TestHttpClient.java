package xin.liujiajun.java.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TestHttpClient {
    public static void main(String[] args) {
        TestHttpClient t = new TestHttpClient();
        t.get();
        t.post();
    }
    public void get(){
        //1. 创建HttpClient 对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //2. 创建HttpGet对象
        HttpGet httpGet = new HttpGet("http://httpbin.org/get");
        CloseableHttpResponse response = null;
        try {
            //3. 执行get请求
            response = httpClient.execute(httpGet);
            System.out.println(response.getStatusLine());
            //4. 获取响应实体
            HttpEntity entity = response.getEntity();
            //5. 处理响应实体
            if (entity !=null){
                System.out.println("长度"+entity.getContentLength());
                System.out.println("内容"+ EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void post(){
        //1. 创建HttpClient 对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //2.创建HttpPost对象
        HttpPost post = new HttpPost("http://www.kdniao.com/ILogin.aspx");
        //3.设置POST请求传递参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("uname","15280365744"));
        params.add(new BasicNameValuePair("pwd","123456"));
        try {
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(params);
            post.setEntity(encodedFormEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //4.执行请求并处理响应
        try {
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity !=null){
                System.out.println("响应内容");
                System.out.println(EntityUtils.toString(entity));
            }
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //5.释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
