package xin.liujiajun.redis.client.serialization;

import cn.hutool.core.convert.impl.StringConverter;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author liujiajun
 * @date 2019-12-30 08:49
 **/
public class StringSerialization implements Serialization {
    @Override
    public byte[] serialize(Object o) throws IOException {
        if (o == null) {
            return null;
        }
        String value = new StringConverter().convert(o, "");
        return StrUtil.utf8Bytes(value);
    }

    @Override
    public Object deserialize(byte[] bytes) throws IOException {
        if (bytes == null) {
            return null;
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public Object deserialize(String str) throws IOException {
        return str;
    }
}
