package xin.liujiajun.java.http;

import okhttp3.*;

import java.io.IOException;

public class TestOkHttp {
    public static void main(String[] args) {
        TestOkHttp http = new TestOkHttp();
//        http.getDataSync();
//        http.getDataAsync();
        http.post();
    }
    //get的同步请求
    public void getDataSync(){
        new Thread(new Runnable() {
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://www.baidu.com/home/msg/data/personalcontent?callback=jQuery11020051777173853880365_1526188555376&num=8&_req_seqid=&sid=&_=1526188555378")
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if(response.isSuccessful()){
                        System.out.println("response.code=" + response.code());
                        System.out.println("response.message=" + response.message());
                        System.out.println("res=" + response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void getDataAsync(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://httpbin.org/get").build();
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {

            }
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    System.out.println("获取数据成功");
                    System.out.println("response.code="+response.code());
                    System.out.println("response.body="+response.body().string());
                }
            }
        });

    }
    private void post(){
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("username","1264231");
        formBody.add("password","1264231");
        Request request = new Request.Builder()
                .url("http://www.kdniao.com/ILogin.aspx")
                .post(formBody.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {

            }
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    System.out.println("response.code="+response.code());
                    System.out.println("response.body="+response.body().string());
                }
            }
        });

    }
}
