package xin.liujiajun.netty.group;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author liujiajun
 * @date 2020-02-18 09:24
 **/
public class Client {


    public ChannelFuture start(String host, int port) throws InterruptedException {

        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();

        b.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(host, port)
                .handler(new ClientInitializer());
        ChannelFuture future = b.connect().sync();
        return future;

    }

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 9001;
        Client client = new Client();
        Client client2 = new Client();
        try {
            client.start(host,port);
            client2.start(host,port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
