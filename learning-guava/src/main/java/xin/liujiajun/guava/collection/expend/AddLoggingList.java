package xin.liujiajun.guava.collection.expend;

import com.google.common.collect.ForwardingList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author liujiajun
 * @description expand demo
 * @create 2019-03-12 17:20
 **/
public class AddLoggingList<E> extends ForwardingList<E> {
    final List<E> delegate;

    public AddLoggingList(List<E> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected List<E> delegate() {
        return delegate;
    }
    @Override
    public void add(int index, E elem){
        System.out.println("记录日志");
        super.add(index,elem);
    }

    @Override
    public boolean add(E element) {
        // standardAdd 实际上调用 this.add(this.size(), element); 返回true
        return standardAdd(element);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        // standardAddAll 实际上也是调用 this.add(E)
        return standardAddAll(collection);
    }

    public static void main(String[] args) {

        ArrayList<Integer> objects = new ArrayList<>();
        AddLoggingList<Integer> integers = new AddLoggingList<>(objects);
        //会输出  记录日志
        integers.add(1);
    }
}
