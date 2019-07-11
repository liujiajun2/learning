package xin.liujiajun.jodd.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * @author liujiajun
 * @description
 * @create 2019-03-23 15:05
 **/
public class HttpRequestDemo {

    public static void main(String[] args) {
//        HttpRequest request = new HttpRequest();
        HttpRequest request = HttpRequest.get("http://jodd.org");
        HttpResponse response = request.send();
        System.out.println(response.headerNames());
        System.out.println(response.header("Connection"));
        System.out.println(response.body());
        System.out.println(response);
//        request.method("GET").protocol("http").host("srv").port(8080).path("/api/jsonws/user/get-user-by-id");
    }
}
