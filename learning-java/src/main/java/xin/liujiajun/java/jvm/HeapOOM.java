package xin.liujiajun.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args : -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=G:\study\hprof -XX:+PrintGCDetails -Xloggc:G:\study\gc\heapoom.txt
 *
 * @author liujiajun
 * @date 2020-11-12 08:48
 **/
public class HeapOOM {

    static class OOMObject{

    }
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }

    }
}
