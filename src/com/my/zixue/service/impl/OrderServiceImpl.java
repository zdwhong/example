package com.my.zixue.service.impl;

import com.my.zixue.dao.ICustomerDao;
import com.my.zixue.dao.IOrderDao;
import com.my.zixue.domain.Customer;
import com.my.zixue.domain.Order;
import com.my.zixue.domain.PageBean;
import com.my.zixue.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create By zdw on 2018/9/8
 */
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private ICustomerDao customerDao;

    @Override
    public List<Order> selectOrdersByCustomerId(String customerId) {

        //先根据customerId查出客户
        Customer customer = customerDao.selectById(Integer.parseInt(customerId));
        //z再根据客户查询订单信息
        List<Order> orderList = orderDao.selectByCustomer(customer);

        return orderList;
    }

    //分页查询订单信息
    @Override
    public PageBean<Order> selectOrdersByPage(String customerId, Integer pageNum, Integer currentCount) {
        PageBean<Order> pageBean = new PageBean<>();
        pageBean.setPageNum(pageNum);
        pageBean.setCurrentCount(currentCount);

        //查询总记录数
        Integer totalCount=orderDao.selectAll();
        pageBean.setTotalCount(totalCount);

        //计算出总页数
        Integer totalPage = (int)Math.ceil(totalCount*1.0/currentCount);
        pageBean.setTotalPage(totalPage);

        //查询出当前页的订单信息
        //先根据customerId查出客户
        Customer customer = customerDao.selectById(Integer.parseInt(customerId));
        //再查询客户的订单
        List<Order> currentContent = orderDao.selectCurrentContent(customer,pageNum,currentCount);
        pageBean.setCurrentContent(currentContent);

        return pageBean;
    }
}
