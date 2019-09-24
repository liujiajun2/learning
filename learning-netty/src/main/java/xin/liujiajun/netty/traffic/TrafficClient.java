package xin.liujiajun.netty.traffic;

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
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujiajun
 * @date 2019-09-10 16:15
 **/
public class TrafficClient {

    private static final String host = "127.0.0.1";
    private static final int port = 9955;
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();

            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ClientInitializer());
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    private static class ClientInitializer extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {

//            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(10 *  1024 * 1024, delimiter));
            ch.pipeline().addLast("LengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4, true));
            ch.pipeline().addLast("LengthFieldPrepender", new LengthFieldPrepender(4, 0));

            ch.pipeline().addLast(new StringEncoder());

            ch.pipeline().addLast(new StringDecoder());

            ch.pipeline().addLast(new TrafficShapingClientHandler());
        }
    }

    private static class TrafficShapingClientHandler extends ChannelInboundHandlerAdapter{
        private static AtomicInteger SEQ = new AtomicInteger(0);
        static final byte [] ECHO_REQ = new byte[1024 * 1024];
        static final String DELIMITER = "$_";
        static String str;
        static ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            AtomicInteger index = new AtomicInteger(0);
            service.scheduleAtFixedRate(()->{
                if (index.get() == 1) {
                    return;
                }
                ByteBuf byteBuf = null;
                for (int i = 0; i < 10; i++) {
                    byteBuf = Unpooled.copiedBuffer(ECHO_REQ);
                    SEQ.getAndAdd(byteBuf.readableBytes());
                    if (ctx.channel().isWritable()) {
                        ctx.write(getStr());
                    }
                }
                ctx.flush();
                int counter = SEQ.getAndSet(0);
                index.incrementAndGet();


                System.out.println("the client send rate is : " + counter/ 1024 + "Kb/s");

            },0,10, TimeUnit.SECONDS);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        }

        public static String getStr(){

            if (str!= null && !"".equalsIgnoreCase(str)) {
                return str;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i <  256 * 1024; i++) {
                builder.append(i);
            }
            str = builder.toString();
            return str;
        }
    }


}
