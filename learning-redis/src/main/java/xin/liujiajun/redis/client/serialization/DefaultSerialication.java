package xin.liujiajun.redis.client.serialization;

import cn.hutool.core.util.StrUtil;
import org.nustaq.serialization.FSTConfiguration;

import java.io.IOException;

/**
 * @author liujiajun
 * @date 2019-12-30 08:49
 **/
public class DefaultSerialication implements Serialization {

    private final static FSTConfiguration fst = FSTConfiguration.createJsonConfiguration();

    @Override
    public byte[] serialize(Object o) throws IOException {
        return fst.asByteArray(o);
    }

    @Override
    public Object deserialize(byte[] bytes) throws IOException {
        return fst.asObject(bytes);
    }

    @Override
    public Object deserialize(String str) throws IOException {
        return fst.asObject(StrUtil.utf8Bytes(str));
    }


}
