package xin.liujiajun.jodd.bean;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liujiajun
 * @create 2019-04-02 10:47
 **/
public class Input {

    private String topic;
    private String place;
    private Integer type;
    private Long startTime;
    private Long endTime;
    private List<String> roomIds;

    public Input(){
        this(",","",0, Instant.now().getEpochSecond(),Instant.now().plusSeconds(3600).getEpochSecond(),new ArrayList<String>());
    }

    public Input(String topic) {
        this(topic,"",0, Instant.now().getEpochSecond(),Instant.now().plusSeconds(3600).getEpochSecond(),new ArrayList<String>());
    }

    public Input(String topic, String place) {
        this(topic,place,0, Instant.now().getEpochSecond(),Instant.now().plusSeconds(3600).getEpochSecond(),new ArrayList<String>());
    }

    public Input(String topic, String place, Integer type) {
        this(topic,place,type, Instant.now().getEpochSecond(),Instant.now().plusSeconds(3600).getEpochSecond(),new ArrayList<String>());
    }

    public Input(String topic, String place, Integer type, Long startTime) {
        this(topic,place,type, startTime,Instant.now().plusSeconds(3600).getEpochSecond(),new ArrayList<String>());
    }

    public Input(String topic, String place, Integer type, Long startTime, Long endTime) {
        this(topic,place,type, startTime,endTime,new ArrayList<String>());

    }

    public Input(String topic, String place, Integer type, Long startTime, Long endTime, List<String> roomIds) {
        this.topic = topic;
        this.place = place;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomIds = roomIds;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<String> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(List<String> roomIds) {
        this.roomIds = roomIds;
    }

    @Override
    public String toString() {
        return "Input{" +
                "topic='" + topic + '\'' +
                ", place='" + place + '\'' +
                ", type=" + type +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", roomIds=" + roomIds +
                '}';
    }
}
