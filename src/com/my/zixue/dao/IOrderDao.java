package com.my.zixue.dao;

import com.my.zixue.domain.Customer;
import com.my.zixue.domain.Order;

import java.util.List;

/**
 * Create By zdw on 2018/9/8
 */
public interface IOrderDao {
    List<Order> selectByCustomer(Customer customer);

    int selectAll();

    List<Order> selectCurrentContent(Customer customer, Integer pageNum, Integer currentCount);
}
