package xin.liujiajun.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;

/**
 * @author liujiajun
 * @date 2019-12-31 14:43
 **/
public class MyVerticle extends AbstractVerticle {

    private HttpServer server;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        server = vertx.createHttpServer()
                .requestHandler(req ->{
                    req.response()
                            .putHeader("Content-Type","text/plain")
                            .end("Hello from Vert.x");
                });

        server.listen(8070,res ->{
           if (res.succeeded()) {
               startFuture.complete();
           }else{
               startFuture.fail(res.cause());
           }
        });
        System.out.println("start");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
    }
}
