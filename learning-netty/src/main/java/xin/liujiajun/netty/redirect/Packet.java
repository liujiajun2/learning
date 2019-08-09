package xin.liujiajun.netty.redirect;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author liujiajun
 * @create 2019-07-29 11:32
 **/
public class Packet {

    private int code;
    private int version;
    private int flag;
    private HashMap<String,String> extFields;
    private transient byte[] body;

    public int getCode() {
        return code;
    }

    public Packet setCode(int code) {
        this.code = code;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public Packet setVersion(int version) {
        this.version = version;
        return this;
    }

    public int getFlag() {
        return flag;
    }

    public Packet setFlag(int flag) {
        this.flag = flag;
        return this;
    }

    public HashMap<String, String> getExtFields() {
        return extFields;
    }

    public Packet setExtFields(HashMap<String, String> extFields) {
        this.extFields = extFields;
        return this;
    }

    public byte[] getBody() {
        return body;
    }

    public Packet setBody(byte[] body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "code=" + code +
                ", version=" + version +
                ", flag=" + flag +
                ", extFields=" + extFields +
                ", body=" + Arrays.toString(body) +
                '}';
    }
}
