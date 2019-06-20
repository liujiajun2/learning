package xin.liujiajun.mybatis.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuJiaJun
 * @date 2018/9/27 10:31
 */
public enum GenderEnum {

    NONE(0,"未知"),
    MALE(1,"男"),
    FEMALE(2,"女");

    private int code;
    private String value;

    static Map<Integer,GenderEnum> enumMap = new HashMap<>();
    static {
        for (GenderEnum type: GenderEnum.values()) {
            enumMap.put(type.getCode(),type);
        }
    }
    GenderEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GenderEnum{" +
                "code=" + code +
                ", value='" + value + '\'' +
                '}';
    }

    public static GenderEnum getEnum(Integer i){
        return enumMap.get(i);
    }
}
