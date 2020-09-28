package xin.liujiajun.netty.group;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @date 2020-02-18 09:45
 **/
public class Server {


    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup(8);
        ServerBootstrap bootstrap = new ServerBootstrap();
//        DefaultEventLoopGroup group = new DefaultEventLoopGroup(8, new ThreadFactory() {
//            private AtomicInteger index = new AtomicInteger(0);
//            @Override
//            public Thread newThread(Runnable r) {
//                Thread thread = new Thread(r);
//                thread.setName("Self-Make-work-" + index.incrementAndGet());
//                return thread;
//            }
//        });
        NioEventLoopGroup group = new NioEventLoopGroup(8, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("Self-Make-work-" + index.incrementAndGet());
                return thread;
            }
        });
        bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(group,new LineBasedFrameDecoder(1024));
                        pipeline.addLast(group,new LoggingHandler());
                        pipeline.addLast(group,new StringEncoder());
                        pipeline.addLast(group,new StringDecoder());

                        pipeline.addLast(group,new ServerHandler());
                    }
                });

        ChannelFuture future = null;
        try {
            future = bootstrap.bind(9001).sync();
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }
}
