package com.my.zixue.domain;

import java.util.List;

/**
 * Create By zdw on 2018/9/8
 * 封装分页数据的bean
 */
public class PageBean<T> {
    private Integer pageNum;// 当前页码
    private Integer totalPage;// 总页数
    private Integer totalCount;// 总条数
    private Integer currentCount;// 每页条数
    private List<T> currentContent; //每页显示的数据

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }

    public List<T> getCurrentContent() {
        return currentContent;
    }

    public void setCurrentContent(List<T> currentContent) {
        this.currentContent = currentContent;
    }
}
