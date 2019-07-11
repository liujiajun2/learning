package xin.liujiajun.netty.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujiajun
 * @create 2019-07-10 12:45
 **/
public class Header {

    private Integer crcCode = 0xabef0101;
    private Integer length;
    private Long sessoinId;
    private Byte type;
    private Byte priority;
    private Map<String,Object> attachement = new HashMap<>();

    public Integer getCrcCode() {
        return crcCode;
    }

    public Header setCrcCode(Integer crcCode) {
        this.crcCode = crcCode;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public Header setLength(Integer length) {
        this.length = length;
        return this;
    }

    public Long getSessoinId() {
        return sessoinId;
    }

    public Header setSessoinId(Long sessoinId) {
        this.sessoinId = sessoinId;
        return this;
    }

    public Byte getType() {
        return type;
    }

    public Header setType(Byte type) {
        this.type = type;
        return this;
    }

    public Byte getPriority() {
        return priority;
    }

    public Header setPriority(Byte priority) {
        this.priority = priority;
        return this;
    }

    public Map<String, Object> getAttachement() {
        return attachement;
    }

    public Header setAttachement(Map<String, Object> attachement) {
        this.attachement = attachement;
        return this;
    }

    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessoinId=" + sessoinId +
                ", type=" + type +
                ", priority=" + priority +
                ", attachement=" + attachement +
                '}';
    }
}
