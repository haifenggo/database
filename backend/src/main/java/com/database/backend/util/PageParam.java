package com.database.backend.util;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PageParam {

    @NotNull(message = "分页参数不能为空")
    private Integer pageNum; // 第几页

    @NotNull(message = "每页数量不能为空")
    @Max(value = 200, message = "每页最大为200")
    private Integer pageSize; // 每页数量

    @Size(max = 10, message = "排序字段最多10")
    @Valid
    private List<SortItem> sortItemList;

    /**
     * 排序DTO类
     */
    @Data
    public static class SortItem {

        @NotNull(message = "排序规则不能为空")
        private Boolean isAsc;

        @NotBlank(message = "排序字段不能为空")
        private String column;
    }
}