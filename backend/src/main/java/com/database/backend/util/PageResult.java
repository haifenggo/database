package com.database.backend.util;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    /**
     * 当前页
     */
    private Long pageNum;

    /**
     * 每页的数量
     */
    private Long pageSize;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 结果集
     */
    private List<T> list;

    private Boolean emptyFlag;


    public PageResult() {}


    public PageResult(Long total, List records) {
        this.total = total;
        this.list = records;
    }


}