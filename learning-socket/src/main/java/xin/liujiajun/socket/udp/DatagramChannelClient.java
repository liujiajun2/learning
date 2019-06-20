package xin.liujiajun.socket.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * @author LiuJiaJun
 * @date 2019/6/20 12:10
 */
public class DatagramChannelClient {
    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress("localhost",9090);
        try(DatagramChannel channel = DatagramChannel.open()){
            channel.configureBlocking(false);
            channel.connect(address);
            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            ByteBuffer buffer = ByteBuffer.allocate(4);
            int n = 0;
            int numbersRead = 0;
            int limit = 100;
            while (true){
                if (numbersRead == limit) {
                    break;
                }
                //等待一分钟
                selector.select(60000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                if (selectionKeys.isEmpty() && n== limit){
                    break;
                }else {
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()){
                            buffer.clear();
                            channel.read(buffer);
                            buffer.flip();
                            int echo = buffer.getInt();
                            System.out.println("Read :" + echo);
                            numbersRead ++;
                        }
                        if (key.isWritable()){
                            buffer.clear();
                            buffer.putInt(n);
                            buffer.flip();
                            channel.write(buffer);
                            System.out.println("Write :" + n);
                            n++;
                            if (n == limit) {
                                //如果包已经写入，切换到只读模式
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                }
            }
            System.out.println("Echoed " + numbersRead + " out of " + limit + " send");
            System.out.println("Success rate " + 100.0 * (numbersRead / limit) + "%");
        }catch (IOException e){
           e.printStackTrace();
        }
    }
}
