package xin.liujiajun.netty.trafficshaping;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author liujiajun
 * @date 2019-09-17 08:54
 **/
public class MyClient {
    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap client = new Bootstrap();
            client.group(group).channel(NioSocketChannel.class).handler(new MyClientInitializer());

            ChannelFuture channelFuture = client.connect("127.0.0.1", 5566).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
