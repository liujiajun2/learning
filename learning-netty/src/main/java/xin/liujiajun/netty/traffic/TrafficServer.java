package xin.liujiajun.netty.traffic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import io.netty.handler.traffic.GlobalChannelTrafficShapingHandler;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @date 2019-09-10 16:15
 **/
public class TrafficServer {

    private static final Integer port = 9955;
    private static final long M = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {

        //创建EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    //指定所使用的NIO 传输channel
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(new ServerInitializer());
            //异步绑定服务器，调用sync方法阻塞等待直到绑定完成
            ChannelFuture f = b.bind().sync();
            //获取closeFuture，阻塞直到完成
            f.channel().closeFuture().sync();
        } finally {
            //释放所有资源
            group.shutdownGracefully().sync();
        }
    }

    private static class ServerInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {

//            //消息类型必须是Bytebuf 或者是ByteBufHolder
//            GlobalTrafficShapingHandler handler = new GlobalTrafficShapingHandler(ch.eventLoop(), 1000, 128, 1000L);
            ChannelTrafficShapingHandler handler = new ChannelTrafficShapingHandler(1024 * 1024, 1024 * 1024,1000L);
            ch.pipeline().addLast("channelTrafficShaping", handler);

            ch.pipeline().addLast("LengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4, true));
            ch.pipeline().addLast("LengthFieldPrepender", new LengthFieldPrepender(4, 0));

            ch.pipeline().addLast(new StringDecoder());

            ch.pipeline().addLast(new StringEncoder());

            ch.pipeline().addLast(new TrafficShapingServerHandler());
        }
    }

    private static class TrafficShapingServerHandler extends SimpleChannelInboundHandler<String> {
        AtomicInteger counter = new AtomicInteger(0);
        static ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        public TrafficShapingServerHandler() {
            service.scheduleAtFixedRate(() -> {
                System.out.println("The server receive client rate is : " + counter.getAndSet(0) / 1024 + "Kb/s");
            }, 0, 1, TimeUnit.SECONDS);
        }

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
            counter.addAndGet(s.getBytes().length);
            System.out.println("now " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n")));

        }
    }
}
