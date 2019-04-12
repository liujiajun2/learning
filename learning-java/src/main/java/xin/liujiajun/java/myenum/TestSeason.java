package xin.liujiajun.java.myenum;

public class TestSeason {

    public static void main(String[] args) {
        Season sping = Season.SPRING;
        System.out.println(sping.toString());
    }
}
//枚举类
class Season{
    private final String seasonName;
    private final String seasonDesc;

    private Season(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
    //创建枚举类的对象,将类的对象设置为public static final
    public static final Season SPRING = new Season("spring","111");
    public static final Season SUMMER = new Season("spring","111");
    public static final Season AUTUMN = new Season("spring","111");
    public static final Season WINTER = new Season("spring","111");
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}