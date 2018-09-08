package com.my.zixue.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Create By zdw on 2018/9/8
 */
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GenericGenerator(name = "orderUUID",strategy = "uuid")
    @GeneratedValue(generator = "orderUUID")
    private String orderNum;
    private String address;
    @Column(precision = 23,scale = 2)//描述数字，长度23，小数位2
    private BigDecimal price;
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")//指定外键名称
    private Customer customer = new Customer();

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
