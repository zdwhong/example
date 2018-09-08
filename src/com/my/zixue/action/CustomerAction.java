package com.my.zixue.action;

import com.my.zixue.domain.Customer;
import com.my.zixue.service.ICustomerService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Create By zdw on 2018/9/8
 */
@Controller
@Scope("prototype")
@Namespace("/customer")
@ParentPackage("struts-default")
public class CustomerAction extends ActionSupport {

    @Autowired
    private ICustomerService customerService;

    @Action(value = "customerList",results = {@Result(name = "success",location = "/customerList.jsp")})
    public String showCustomerList(){
        List<Customer> list = customerService.findAll();
        ServletActionContext.getContext().getValueStack().set("customerList",list);
        return SUCCESS;
    }

    //deleteCustomer
    @Action(value = "delCustomer",results = {@Result(name = "success",location = "customerList",type = "redirectAction")})
    public String deleteCustomer(){
        String id = ServletActionContext.getRequest().getParameter("id");
        customerService.deleteById(new Integer(id));
        return SUCCESS;
    }
}
