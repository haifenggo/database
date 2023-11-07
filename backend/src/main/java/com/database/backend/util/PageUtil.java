package com.database.backend.util;

import java.util.List;


public class PageUtil {


    /**
     * 转换为 PageResult 对象
     */
    public static <E> PageResult<E> convert2PageResult(PageParam pageParam, Long total, List<E> sourceList) {
        PageResult<E> pageResult = new PageResult<>();
        // 计算页码和每页显示数量
        long pageNum = Long.valueOf(pageParam.getPageNum());
        long pageSize = Long.valueOf(pageParam.getPageSize());
        // 计算总页数
        long pages = (long) Math.ceil((double) total / pageSize);
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setTotal(total);
        pageResult.setPages(pages);
        pageResult.setList(sourceList);
        pageResult.setEmptyFlag(sourceList.isEmpty());
        return pageResult;
    }

    public static void addSortClause(StringBuffer sqlBuilder, PageParam pageParam) {
        List<PageParam.SortItem> sortItems = pageParam.getSortItemList();
        if (sortItems != null && !sortItems.isEmpty()) {
            sqlBuilder.append(" ORDER BY ");
            for (int i = 0; i < sortItems.size(); i++) {
                PageParam.SortItem sortItem = sortItems.get(i);
                String column = sortItem.getColumn();
                boolean isAsc = sortItem.getIsAsc();
                // 添加排序字段和排序规则
                sqlBuilder.append(column)
                        .append(" ")
                        .append(isAsc ? "ASC" : "DESC");
                // 添加逗号分隔符
                if (i < sortItems.size() - 1) {
                    sqlBuilder.append(", ");
                }
            }
        }
    }

    public static void addLimit(StringBuffer sqlBuilder){
        sqlBuilder.append(" LIMIT ?, ?");
    }
}
