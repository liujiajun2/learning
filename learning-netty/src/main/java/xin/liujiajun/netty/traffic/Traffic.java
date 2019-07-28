package xin.liujiajun.netty.traffic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liujiajun
 * @create 2019-07-25 19:06
 **/
public class Traffic {


    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();

        b.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new AppChannleInializer());

        ChannelFuture future = b.bind(8099).sync();
        future.channel().closeFuture().sync();
    }
}
