package com.my.zixue.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.my.zixue.domain.Order;
import com.my.zixue.domain.PageBean;
import com.my.zixue.service.IOrderService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Create By zdw on 2018/9/8
 */
@Controller
@Scope("prototype")
@Namespace("/order")
@ParentPackage("struts-default")
public class OrderAction {

    @Autowired
    private IOrderService orderService;

    @Action(value = "orderInfo")
    public void orderInfoByCustomerId(){
        //----------------------------------------------这是不分页查询操作开始----------------------------
        /*ServletActionContext.getResponse().setCharacterEncoding("utf-8");

        String customerId = ServletActionContext.getRequest().getParameter("customerId");
        List<Order> orderList = orderService.selectOrdersByCustomerId(customerId);
        //通过json写道页面
        //String json = JSONObject.toJSONString(orderList);
        *//**
         * 直接写的时候，json的格式是这样的：
         * "[{\"address\":\"长沙市\",\"customer\":{\"cusImgSrc\":\"/upload/1111.jpg\",
         * \"cusName\":\"fox\",\"cusPhone\":\"15567675890\",\"id\":2,\"orders\":[{\"$ref\":\"$[0]\"},
         * {\"address\":\"长沙市2\",\"customer\":{\"$ref\":\"$[0].customer\"},\"orderNum\":\"1124455\",\"price\":3000.00}]},
         * \"orderNum\":\"112233\",\"price\":2000.00},{\"$ref\":\"$[0].customer.orders[1]\"}]"
         *//*
        //但是有些是我们页面不需要的，而且这里还用到了循环引用：[{\"$ref\":\"$[0]\"}，这都是我们需要考虑的
        // 对json数据进行过滤，并且取消循环引用
        PropertyFilter filter = new PropertyFilter() {

            @Override
            public boolean apply(Object arg0, String fieldName, Object arg2) {
                if ("cusPhone".equalsIgnoreCase(fieldName)) {//表示去除掉的自段信息
                    return false;
                }
                if ("id".equalsIgnoreCase(fieldName)) {
                    return false;
                }
                if ("orders".equalsIgnoreCase(fieldName)) {
                    return false;
                }
                *//*if ("address".equalsIgnoreCase(fieldName)) {
                    return false;
                }*//*

                return true;
            }
        };
        //SerializerFeature.DisableCircularReferenceDetect : 取消循环引用
        String json = JSONObject.toJSONString(orderList,filter, SerializerFeature.DisableCircularReferenceDetect);
        try {
            ServletActionContext.getResponse().getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //----------------------------------------------这是不分页查询操作结束----------------------------


        //----------------------------------------------这是不分页查询开始--------------------------------
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");

        String customerId = ServletActionContext.getRequest().getParameter("customerId");//接收客户id
        Integer pageNum = Integer.parseInt( ServletActionContext.getRequest().getParameter("pageNum"));//当前页码
        Integer currentCount = Integer.parseInt( ServletActionContext.getRequest().getParameter("currentCount"));//每页记录数

        PageBean<Order> pageBean = orderService.selectOrdersByPage(customerId,pageNum,currentCount);//调用业务层分页查询方法
        //通过json写道页面
        //但是有些是我们页面不需要的，而且这里还用到了循环引用：[{\"$ref\":\"$[0]\"}，这都是我们需要考虑的
        // 对json数据进行过滤，并且取消循环引用
        PropertyFilter filter = new PropertyFilter() {

            @Override
            public boolean apply(Object arg0, String fieldName, Object arg2) {
                if ("cusPhone".equalsIgnoreCase(fieldName)) {//表示去除掉的自段信息
                    return false;
                }
                if ("id".equalsIgnoreCase(fieldName)) {
                    return false;
                }
                if ("orders".equalsIgnoreCase(fieldName)) {
                    return false;
                }
                /*if ("address".equalsIgnoreCase(fieldName)) {
                    return false;
                }*/

                return true;
            }
        };
        //SerializerFeature.DisableCircularReferenceDetect : 取消循环引用
        String json = JSONObject.toJSONString(pageBean,filter, SerializerFeature.DisableCircularReferenceDetect);
        try {
            ServletActionContext.getResponse().getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //----------------------------------------------这是分页查询操作结束------------------------------
    }
}
