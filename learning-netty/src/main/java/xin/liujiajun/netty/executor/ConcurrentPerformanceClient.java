package xin.liujiajun.netty.executor;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiajun
 * @date 2019-10-11 13:45
 **/
public class ConcurrentPerformanceClient {


    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

            Bootstrap b = new Bootstrap();

            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress("127.0.0.1",9979))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    });
            for (int i = 0; i < 1; i++) {
                ChannelFuture f = b.connect().sync();
            }
    }
    private static class ClientHandler extends ChannelInboundHandlerAdapter {
        static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            //将一个连接 1s发送100次 改成 100个连接 1s发送一次，即可达到100QPS
            scheduledExecutorService.scheduleAtFixedRate(()->{
                for (int i = 0; i < 100; i++) {
                    ByteBuf buffer = Unpooled.buffer(100);
                    for (int j = 0; j < buffer.capacity(); j++) {
                        buffer.writeByte((byte) j);
                    }
                    ctx.writeAndFlush(buffer);
                }

            },0,1000, TimeUnit.MILLISECONDS);
        }
    }
}
