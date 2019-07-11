package xin.liujiajun.guava.collection.newconllection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;
import java.util.Set;

/**
 * @author liujiajun
 * @description table demo
 * @create 2019-03-12 13:55
 **/
public class TableDemo {

    public static void main(String[] args) {
        Table<Integer, Integer, String> table = HashBasedTable.create();
        table.put(0,0,"1");
        table.put(0,1,"2");
        table.put(0,2,"3");
        table.put(1,0,"4");
        table.put(1,1,"5");
        table.put(1,2,"6");
        table.put(2,0,"7");
        table.put(2,1,"8");
        table.put(2,2,"9");

        Map<Integer, Map<Integer, String>> integerMapMap = table.rowMap();
        //{0={0=1, 1=2, 2=3}, 1={0=4, 1=5, 2=6}, 2={0=7, 1=8, 2=9}}
        System.out.println(integerMapMap);
        Map<Integer, String> row = table.row(1);
        //{0=4, 1=5, 2=6}
        System.out.println(row);
        Map<Integer, String> column = table.column(1);
        //{0=2, 1=5, 2=8}
        System.out.println(column);
        Set<Table.Cell<Integer, Integer, String>> cells = table.cellSet();
        //[(0,0)=1, (0,1)=2, (0,2)=3, (1,0)=4, (1,1)=5, (1,2)=6, (2,0)=7, (2,1)=8, (2,2)=9]
        System.out.println(cells);
    }
}
