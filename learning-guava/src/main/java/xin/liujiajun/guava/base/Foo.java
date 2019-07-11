package xin.liujiajun.guava.base;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author liujiajun
 * @description foo demo
 * @create 2019-03-12 09:16
 **/
public class Foo {
    @Nullable
    String sortedBy;
    int notSortedBy;

    public Foo(@Nullable String sortedBy, int notSortedBy) {
        this.sortedBy = sortedBy;
        this.notSortedBy = notSortedBy;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "sortedBy='" + sortedBy + '\'' +
                ", notSortedBy=" + notSortedBy +
                '}';
    }
}
