package xin.liujiajun.redis.pubsub;

import cn.hutool.core.lang.UUID;

/**
 * @author liujiajun
 * @date 2019-12-30 14:15
 **/
public class BasePublishEvent {

    private String id = UUID.randomUUID().toString(true);

    private Long time;

    private String type;

    public String getId() {
        return id;
    }

    public BasePublishEvent setId(String id) {
        this.id = id;
        return this;
    }

    public Long getTime() {
        return time;
    }

    public BasePublishEvent setTime(Long time) {
        this.time = time;
        return this;
    }

    public String getType() {
        return type;
    }

    public BasePublishEvent setType(String type) {
        this.type = type;
        return this;
    }
}
