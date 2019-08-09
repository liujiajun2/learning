package xin.liujiajun.netty.ssl.both;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;
import java.net.InetSocketAddress;

/**
 * @author liujiajun
 * @date 2019-08-08 14:07
 **/
public class SslClient {
    public Bootstrap start()  {
        String host = "127.0.0.1";
        int port = 18090;
        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();

        b.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        SslContext clientContext = SslContextFactory.getClientContext();
                        boolean client = clientContext.isClient();
                        System.out.println("is client : {} " + client);
                        SSLEngine sslEngine = clientContext.newEngine(ch.pipeline().channel().alloc(),host,port);
                        System.out.println(sslEngine.getPeerHost());
                        System.out.println(sslEngine.getPeerPort());
                        sslEngine.setUseClientMode(true);
                        ch.pipeline().addLast("sslHandler",new SslHandler(sslEngine));
                        ch.pipeline().addLast(new SslClientHandler());
                    }
                });
        return b;

    }

    public static void main(String[] args) throws InterruptedException {
        Bootstrap b = new SslClient().start();
        ChannelFuture future = b.connect().sync();
    }
}
