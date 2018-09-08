package com.my.zixue.service.impl;

import com.my.zixue.dao.ICustomerDao;
import com.my.zixue.domain.Customer;
import com.my.zixue.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create By zdw on 2018/9/8
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public void addCustomer(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public void deleteById(Integer id) {
        //先根据id查询，然后再删除
        Customer c = customerDao.selectById(id);
        if(c!=null){
            customerDao.deleteById(c);
        }

    }
}
