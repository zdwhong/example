package com.my.zixue.action;

import com.my.zixue.domain.Customer;
import com.my.zixue.service.ICustomerService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;

/**
 * Create By zdw on 2018/9/8
 * 文件上传的action，跟CustomerAction是同一个package下的
 */
@Controller
@Scope("prototype")
@Namespace("/customer")
@ParentPackage("struts-default")
public class ImageUploadAction extends ActionSupport {
    @Autowired
    private ICustomerService customerService;

    //根据struts2的文档知道，他是通过拦截器进行文件上传的，它要求我们按照它的要求来就可以进行文件上传
    private File cusImg; // 用于接收上传的文件，名字要和页面的file属性的名称一致
    private String cusImgFileName; // 用来接收上传文件名称,这个变量名是由cusImg+FileName组成的，规定的

    private String cusName;//这两个属性是用来进行属性封装，接收页面的参数的
    private String cusPhone;//下面提供get/set方法

    //这里的input，是struts2默认的当错误时，返回的一个视图
    @Action(value = "addCustomer",results = {@Result(name = "success",location = "customerList",type = "redirectAction"),
                                            @Result(name = "input",location = "error.jsp")})
    public String addCustomer() {

        // 处理上传文件，保存到服务器端
        File destFile = new File(ServletActionContext.getServletContext().getRealPath("/upload"), cusImgFileName);
        System.out.println("------------------------------------------------------------");
        System.out.println(cusImg);
        System.out.println(destFile);
        System.out.println("------------------------------------------------------------");
        try {
            FileUtils.copyDirectory(cusImg,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Customer customer = new Customer();
        customer.setCusName(cusName);
        customer.setCusPhone(cusPhone);

        String cusImgsrc = ServletActionContext.getRequest().getContextPath() + "/upload/" + cusImgFileName; // /ssh-exam/upload/1.jpg
        //String cusImgsrc = destFile.getAbsolutePath();
        customer.setCusImgSrc(cusImgsrc);

        //调用业务层添加客户信息
        customerService.addCustomer(customer);
        return SUCCESS;
    }

    public File getCusImg() {
        return cusImg;
    }

    public void setCusImg(File cusImg) {
        this.cusImg = cusImg;
    }

    public String getCusImgFileName() {
        return cusImgFileName;
    }

    public void setCusImgFileName(String cusImgFileName) {
        this.cusImgFileName = cusImgFileName;
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
}
