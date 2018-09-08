package com.my.zixue.dao;

import com.my.zixue.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create By zdw on 2018/9/8
 */
public interface ICustomerDao {
    List<Customer> findAll();

    void save(Customer customer);

    void deleteById(Customer customer);

    Customer selectById(Integer id);
}
