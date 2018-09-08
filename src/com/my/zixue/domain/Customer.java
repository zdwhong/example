package com.my.zixue.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Create By zdw on 2018/9/8
 */
@Entity
@Table(name = "t_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cusName;

    private String cusPhone;
    private String cusImgSrc;
    @OneToMany(targetEntity = Order.class,mappedBy = "customer")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)//删除客户级联删除订单
    private Set<Order> orders=new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusImgSrc() {
        return cusImgSrc;
    }

    public void setCusImgSrc(String cusImgSrc) {
        this.cusImgSrc = cusImgSrc;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
