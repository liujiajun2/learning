package xin.liujiajun.java.myenum;

public class Menu {

    public static void main(String[] args) {
        //values()
        Season1[] season1s = Season1.values();
        for (int i = 0; i < season1s.length; i++) {
            System.out.println(season1s[i]);
            season1s[i].show();
        }
        //valueOf()
        String str = "SPRING";
        Season1 season1 = Season1.valueOf(str);
        System.out.println(season1);

        Thread.State [] states = Thread.State.values();
        for (int i = 0; i <states.length ; i++) {
            System.out.println(states);
        }
    }
}
interface Info{
    void show();
}
enum Season1 implements Info{
    SPRING("SPRING","11111"){
        public void show() {
            System.out.println("11111");
        }
    },
    SUMMER("SUMMER","22222"){
        public void show() {
            System.out.println("22222");
        }
    },
    AUTUMN("AUTUMN","33333"){
        public void show() {
            System.out.println("33333");
        }
    },
    WINTER("WINTER","44444"){
        public void show() {
            System.out.println("44444");
        }
    };

    private final String seasonName;
    private final String seasonDesc;

    private Season1(String seasonName,String seasonDesc){
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
    @Override
    public String toString() {
        return "Season1{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}