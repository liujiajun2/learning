package xin.liujiajun.springboot.test.sentinel;

/**
 * 拦截详情日志(限流，降级还是系统保护)
 *
 * @author liujiajun
 * @date 2019-09-24 15:07
 **/
public class BlockInfo {

    /**
     * 2014-06-20 16:35:10|1|sayHello(java.lang.String,long),FlowException,default,origin|61,0
     */
    /**
     * 2014-06-20 16:35:10：时间戳；
     */
    private String time;
    /**
     * 1：序号；
     */
    private Integer sequence;
    /**
     *  sayHello(java.lang.String,long)：资源描述符；
     */
    private String resource;
    /**
     * XXXException：表示被限制的种类。FlowException 表示被限流，DegradeException 表示被降级，SystemException 表示被系统保护
     * default规则上配置的限制应用；
     * origin：实际被限制的来源应用，可能为空字符串；
     */
    private String limitKind;
    private String defaultLimit;
    private String actualLimit;
    /**
     * 61,0：61 代表这一秒内限流降级发生的次数，0 无含义（可忽略)。
     */
    private Integer limit;

    public String getTime() {
        return time;
    }

    public BlockInfo setTime(String time) {
        this.time = time;
        return this;
    }

    public Integer getSequence() {
        return sequence;
    }

    public BlockInfo setSequence(Integer sequence) {
        this.sequence = sequence;
        return this;
    }

    public String getResource() {
        return resource;
    }

    public BlockInfo setResource(String resource) {
        this.resource = resource;
        return this;
    }

    public String getLimitKind() {
        return limitKind;
    }

    public BlockInfo setLimitKind(String limitKind) {
        this.limitKind = limitKind;
        return this;
    }

    public String getDefaultLimit() {
        return defaultLimit;
    }

    public BlockInfo setDefaultLimit(String defaultLimit) {
        this.defaultLimit = defaultLimit;
        return this;
    }

    public String getActualLimit() {
        return actualLimit;
    }

    public BlockInfo setActualLimit(String actualLimit) {
        this.actualLimit = actualLimit;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public BlockInfo setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public String toString() {
        return "BlockInfo{" +
                "time='" + time + '\'' +
                ", sequence=" + sequence +
                ", resource='" + resource + '\'' +
                ", limitKind='" + limitKind + '\'' +
                ", defaultLimit='" + defaultLimit + '\'' +
                ", actualLimit='" + actualLimit + '\'' +
                ", limit=" + limit +
                '}';
    }
}
