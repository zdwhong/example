package com.my.zixue.service;

import com.my.zixue.domain.Customer;

import java.util.List;

/**
 * Create By zdw on 2018/9/8
 */
public interface ICustomerService {
    List<Customer> findAll();

    void addCustomer(Customer customer);

    void deleteById(Integer id);
}
