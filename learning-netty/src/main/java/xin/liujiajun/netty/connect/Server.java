package xin.liujiajun.netty.connect;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liujiajun
 * @date 2019-08-07 15:11
 **/
public class Server {
    public void start() throws InterruptedException {
        ServerHandler handler = new ServerHandler();
        //创建EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    //指定所使用的NIO 传输channel
                    .channel(NioServerSocketChannel.class)
                    .localAddress(18090)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //EchoServerHandler 被标注为@Shareable,所以我们可以总是使用同样的实例
                            socketChannel.pipeline().addLast(handler);
                        }
                    });
            //异步绑定服务器，调用sync方法阻塞等待直到绑定完成
            ChannelFuture f = b.bind().sync();
            //获取closeFuture，阻塞直到完成
            f.channel().closeFuture().sync();
        }finally {
            //释放所有资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Server().start();
    }
}
