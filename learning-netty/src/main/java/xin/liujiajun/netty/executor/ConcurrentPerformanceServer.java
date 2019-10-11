package xin.liujiajun.netty.executor;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @date 2019-10-11 13:33
 **/
public class ConcurrentPerformanceServer {

    static EventExecutorGroup executors = new DefaultEventExecutorGroup(100);

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        ServerHandler serverHandler = new ServerHandler();
        b.group(boosGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(executors,serverHandler);
                    }
                });

        try {
            ChannelFuture sync = b.bind(9979).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            workGroup.shutdownGracefully();
            boosGroup.shutdownGracefully();
        }
    }
    @ChannelHandler.Sharable
    private static class ServerHandler extends ChannelInboundHandlerAdapter {

        AtomicInteger counter = new AtomicInteger(0);
        static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        public ServerHandler(){
            scheduledExecutorService.scheduleAtFixedRate(()->{
                int qps = counter.getAndSet(0);
                System.out.println("The server QPS is : " + qps);
            },0,1000, TimeUnit.MILLISECONDS);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);

        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ((ByteBuf)msg).release();
            counter.incrementAndGet();
            Random random = new Random();
            try{
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
