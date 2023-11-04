package com.database.backend.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @描述
 * @日期 2023/10/11 10:03
 */
@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DataTracer {
    private Integer id;
    private Integer userId;
    private Integer type;
    private String content;
    private LocalDateTime createTime;
}
