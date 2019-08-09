package xin.liujiajun.netty.ssl.both;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

import javax.net.ssl.SSLEngine;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2019-08-08 14:07
 **/
public class SslServer {

    public void start() throws InterruptedException {
        SslServerHandler handler = new SslServerHandler();
        //创建EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    //指定所使用的NIO 传输channel
                    .channel(NioServerSocketChannel.class)
                    .localAddress(18090)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            SSLEngine sslEngine = SslContextFactory.getServerContext().newEngine(ch.pipeline().channel().alloc());
                            //第一个条目就是对等方自己的证书
//                            sslEngine.getSession().getPeerCertificates();
                            sslEngine.setUseClientMode(false);
                            sslEngine.setNeedClientAuth(true);
                            sslEngine.setEnabledCipherSuites(new String[]{"TLS_RSA_WITH_AES_128_CBC_SHA"});

                            // 设置ssl握手超时时间
                            SslHandler sslHandler = new SslHandler(sslEngine);
                            sslHandler.setHandshakeTimeout(120, TimeUnit.SECONDS);
                            Future<Channel> handshakeFuture = sslHandler.handshakeFuture();
                            handshakeFuture.addListener(new FutureListener<Channel>() {
                                @Override
                                public void operationComplete(Future<Channel> future) throws Exception {
                                    if (!future.isSuccess()){
                                        System.out.println("SslHandler, handshake failed：" + future.cause());
                                    }else {
                                        System.out.println("SslHandler, handshake ok");
                                    }
                                }
                            });
                            ch.pipeline().addLast("ssl",sslHandler);
                            //EchoServerHandler 被标注为@Shareable,所以我们可以总是使用同样的实例
                            ch.pipeline().addLast(handler);
                        }
                    });
            //异步绑定服务器，调用sync方法阻塞等待直到绑定完成
            ChannelFuture f = b.bind().sync();
            //获取closeFuture，阻塞直到完成
            f.channel().closeFuture().sync();
        }finally {
            //释放所有资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new SslServer().start();
    }
}
