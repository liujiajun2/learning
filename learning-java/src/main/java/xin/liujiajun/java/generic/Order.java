package xin.liujiajun.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义泛型列
 */
public class Order<T> {
    private String orderName;
    private int orderId;
    private T t;
    List<T> list = new ArrayList<T>();
    public void add(){
        list.add(t);
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public <E> E getE(E e){
        return e;
    }
    public <E> List<E> fromArrayToLsist(E[] arr,List<E> list){
        for (E e1:arr) {
            list.add(e1);
        }
        return list;
    }
    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", t=" + t +
                ", list=" + list +
                '}';
    }
}
