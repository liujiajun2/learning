package xin.liujiajun.netty.redirect;

import com.google.gson.GsonBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author liujiajun
 * @create 2019-07-29 11:33
 **/
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        //包的内容  length  || headerLength || headerData || body

        //length
        int length = byteBuf.readInt();

        //header length
        int headerLength = byteBuf.readInt();

        //3. header data
        byte[] bytes = new byte[headerLength];
        byteBuf.readBytes(bytes);
        Packet packet =  new GsonBuilder().setPrettyPrinting()
                .disableHtmlEscaping()
                .create()
                .fromJson(new String(bytes),Packet.class);

        //4. body data
        int bodyLength = length - 4 - headerLength;
        byte[] bodyBytes = new byte[bodyLength];
        byteBuf.readBytes(bodyBytes);

        packet.setBody(bodyBytes);

        list.add(packet);
    }

}
