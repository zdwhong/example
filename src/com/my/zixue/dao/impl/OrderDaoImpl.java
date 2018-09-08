package com.my.zixue.dao.impl;

import com.my.zixue.dao.IOrderDao;
import com.my.zixue.domain.Customer;
import com.my.zixue.domain.Order;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create By zdw on 2018/9/8
 */
@Repository
public class OrderDaoImpl extends HibernateDaoSupport implements IOrderDao {
    @Autowired
    @Qualifier("sessionFactory")
    private void setSession(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<Order> selectByCustomer(Customer customer) {
        return (List<Order>) this.getHibernateTemplate().find("from Order o where o.customer=?", customer);
    }

    @Override
    public int selectAll() {
        return ((Long) this.getHibernateTemplate().find("select count(*) from Order").iterator().next()).intValue();
    }

    //根据客户，查询某一页订单信息
    @Override
    public List<Order> selectCurrentContent(Customer customer, Integer pageNum, Integer currentCount) {
        DetachedCriteria dc = DetachedCriteria.forClass(Order.class);
        dc.add(Restrictions.eq("customer", customer));
        return (List<Order>) this.getHibernateTemplate().findByCriteria(dc, (pageNum - 1) * currentCount, currentCount);
    }
}
