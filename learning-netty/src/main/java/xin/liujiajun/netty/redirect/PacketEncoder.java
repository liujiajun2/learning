package xin.liujiajun.netty.redirect;

import com.google.gson.GsonBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author liujiajun
 * @create 2019-07-29 11:33
 **/
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        //1.length
        int length = 4;

        //2. header data length
        byte[] headerData = new GsonBuilder().setPrettyPrinting()
                .disableHtmlEscaping()
                .create()
                .toJson(packet).getBytes();
        length += headerData.length;

        //3. body length
        byte[] body = packet.getBody();
        if (packet.getBody() != null) {
            length += body.length;
        }

        byteBuf.writeInt(length);

        byteBuf.writeInt(headerData.length);

        byteBuf.writeBytes(headerData);

        byteBuf.writeBytes(body);

    }
}
