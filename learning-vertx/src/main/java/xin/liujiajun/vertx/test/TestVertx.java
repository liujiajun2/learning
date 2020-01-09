package xin.liujiajun.vertx.test;

import io.vertx.core.Vertx;
import xin.liujiajun.vertx.MyVerticle;

/**
 * @author liujiajun
 * @date 2019-12-31 14:38
 **/
public class TestVertx {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
//        vertx.createHttpClient()
//                .get("")
//                .end();

//        vertx.setPeriodic(1000,id ->{
//            System.out.println("  ff " + id);
//        });

        vertx.deployVerticle(new MyVerticle());
    }
}
