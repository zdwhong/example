package com.my.zixue.service;

import com.my.zixue.domain.Order;
import com.my.zixue.domain.PageBean;

import java.util.List;

/**
 * Create By zdw on 2018/9/8
 */
public interface IOrderService {
    List<Order> selectOrdersByCustomerId(String customerId);

    PageBean<Order> selectOrdersByPage(String customerId, Integer pageNum, Integer currentCount);
}
