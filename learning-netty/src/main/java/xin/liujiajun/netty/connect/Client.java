package xin.liujiajun.netty.connect;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import xin.liujiajun.netty.client.EchoClientHandler;

import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * @author liujiajun
 * @date 2019-08-07 15:11
 **/
public class Client {

    public Bootstrap start()  {
        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();

        b.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("127.0.0.1", 18090))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        return b;

    }

    public static void main(String[] args) throws InterruptedException {
        long strt = System.currentTimeMillis();
        Bootstrap b = new Client().start();
        ArrayList<Channel> channels = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            channels.add(b.connect().sync().channel());
        }
        System.out.println(channels.size());
        System.out.println("times " + (System.currentTimeMillis() - strt) + "ms");

    }
}
