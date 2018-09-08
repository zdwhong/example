package com.my.zixue.dao.impl;

import com.my.zixue.dao.ICustomerDao;
import com.my.zixue.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create By zdw on 2018/9/8
 */
@Service("customerDao")
public class CustomerDaoImpl extends HibernateDaoSupport implements ICustomerDao {

    @Autowired
    @Qualifier("sessionFactory")//会到applicationContext.xml配置文件中找到我们配置的用来管理hibernate的LocalSessionFactory
    private void setSession(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) this.getHibernateTemplate().find(("from Customer"));
    }

    @Override
    public void save(Customer customer) {
        this.getHibernateTemplate().save(customer);
    }

    @Override
    public void deleteById(Customer customer) {

        this.getHibernateTemplate().delete(customer);
    }

    @Override
    public Customer selectById(Integer id) {
        return this.getHibernateTemplate().get(Customer.class,id);
    }
}
