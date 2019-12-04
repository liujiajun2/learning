package xin.liujiajun.mq.kafka.serialization;

/**
 * @author liujiajun
 * @date 2019-12-03 20:05
 **/
public class Customer {

    private int customerId;
    private String customerName;

    public Customer() {
    }

    public Customer(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Customer setCustomerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Customer setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }
}
