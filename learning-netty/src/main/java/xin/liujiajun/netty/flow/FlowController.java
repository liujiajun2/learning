package xin.liujiajun.netty.flow;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 流量控制
 *
 * @author liujiajun
 * @create 2019-07-23 10:24
 **/
public class FlowController {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            NioEventLoopGroup group = new NioEventLoopGroup();
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture future = b.connect("localhost", 9988);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()){
                        System.out.println("not success");
                    }
                }
            });
            future.awaitUninterruptibly();
            boolean active = future.channel().isActive();
            System.out.println("active :" + active);
        }
    }
}
