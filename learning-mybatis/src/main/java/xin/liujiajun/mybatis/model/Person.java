package xin.liujiajun.mybatis.model;

import java.time.Instant;
import java.util.List;

/**
 * @author LiuJiaJun
 * @date 2018/9/27 10:40
 */
public class Person {
    private Integer id;
    private String name;
    private GenderEnum sex;
    private List<String> interest;
    private Instant createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderEnum getSex() {
        return sex;
    }

    public void setSex(GenderEnum sex) {
        this.sex = sex;
    }

    public List<String> getInterest() {
        return interest;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PersonMapper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", interest=" + interest +
                ", createTime=" + createTime +
                '}';
    }


}
