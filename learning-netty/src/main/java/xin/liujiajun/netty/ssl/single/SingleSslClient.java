package xin.liujiajun.netty.ssl.single;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.net.InetSocketAddress;

/**
 * @author liujiajun
 * @date 2019-08-08 14:07
 **/
public class SingleSslClient {
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
                        SslContext client = SslContextFactory.getClient();
                        SSLEngine sslEngine = client.newEngine(ch.pipeline().channel().alloc(), host, port);
                        sslEngine.setUseClientMode(true);
                        ch.pipeline().addLast("sslHandler",new SslHandler(sslEngine));
                        ch.pipeline().addLast(new SingleSslClientHandler());
                    }
                });
        return b;

    }

    public static void main(String[] args) throws InterruptedException {
        Bootstrap b = new SingleSslClient().start();
        ChannelFuture future = b.connect().sync();
    }
}
