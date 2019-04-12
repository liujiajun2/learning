package xin.liujiajun.java.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestHttpUrlConnection {

    public static void main(String[] args) {
        TestHttpUrlConnection t = new TestHttpUrlConnection();
        t.get();
        t.post();
    }
    public void get(){
        String path = "http://v.juhe.cn/exp/index?key=key&com=sf&no=575677355677";
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);//设置连接超时
            conn.setDoInput(true);//是否打开输入流
            conn.setDoOutput(true);//是否打开输出流
            conn.connect();//连接
            int responseCode = conn.getResponseCode();
            String msg = "";
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
                String result;
                while ((result = reader.readLine()) != null){
                    msg += result;
                }
                reader.close();
            }
            conn.disconnect();
            System.out.println(msg);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void post(){
        String path = "http://v.juhe.cn/exp/index?";
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-type","application/x-www-form-urlencoded");
            conn.connect();
            //处理输入输出
            String params = "key=key&com=sf&no=575677355677";
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(params.getBytes());
            outputStream.flush();
            outputStream.close();
            String message = "";
            int code = conn.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) !=null){
                    message += line;
                }
                reader.close();
            }
            conn.disconnect();
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
