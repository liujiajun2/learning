package xin.liujiajun.springboot.test.sentinel;

/**
 * 所有的资源都会产生秒级日志
 *
 * @author liujiajun
 * @date 2019-09-24 15:09
 **/
public class MetricsInfo {

    /**
     * 1532415661000|2018-07-24 15:01:01|sayHello(java.lang.String)|12|3|4|2|295
     */
    /**
     * 1532415661000：时间戳
     */
    private Long timestamp;
    /**
     * 2018-07-24 15:01:01：格式化之后的时间戳
     */
    private String time;
    /**
     * sayHello(java.lang.String)：资源名
     */
    private String resource;
    /**
     * 12：表示到来的数量，即此刻通过 Sentinel 规则 check 的数量（passed QPS）
     */
    private Integer passCount;
    /**
     * 3：实际该资源被拦截的数量（blocked QPS）
     */
    private Integer blockCount;
    /**
     * 4：每秒结束的资源个数（完成调用），包括正常结束和异常结束的情况（exit QPS）
     */
    private Integer completeCount;
    /**
     * 2：异常的数量
     */
    private Integer exceptionCount;
    /**
     * 295：资源的平均响应时间（RT）
     */
    private Integer rt;

    public Long getTimestamp() {
        return timestamp;
    }

    public MetricsInfo setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getTime() {
        return time;
    }

    public MetricsInfo setTime(String time) {
        this.time = time;
        return this;
    }

    public String getResource() {
        return resource;
    }

    public MetricsInfo setResource(String resource) {
        this.resource = resource;
        return this;
    }

    public Integer getPassCount() {
        return passCount;
    }

    public MetricsInfo setPassCount(Integer passCount) {
        this.passCount = passCount;
        return this;
    }

    public Integer getBlockCount() {
        return blockCount;
    }

    public MetricsInfo setBlockCount(Integer blockCount) {
        this.blockCount = blockCount;
        return this;
    }

    public Integer getCompleteCount() {
        return completeCount;
    }

    public MetricsInfo setCompleteCount(Integer completeCount) {
        this.completeCount = completeCount;
        return this;
    }

    public Integer getExceptionCount() {
        return exceptionCount;
    }

    public MetricsInfo setExceptionCount(Integer exceptionCount) {
        this.exceptionCount = exceptionCount;
        return this;
    }

    public Integer getRt() {
        return rt;
    }

    public MetricsInfo setRt(Integer rt) {
        this.rt = rt;
        return this;
    }

    @Override
    public String toString() {
        return "MetricsInfo{" +
                "timestamp=" + timestamp +
                ", time='" + time + '\'' +
                ", resource='" + resource + '\'' +
                ", passCount=" + passCount +
                ", blockCount=" + blockCount +
                ", completeCount=" + completeCount +
                ", exceptionCount=" + exceptionCount +
                ", rt=" + rt +
                '}';
    }
}
