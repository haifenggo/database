package com.database.backend.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @描述
 * @日期 2023/10/11 10:01
 */
@Data
@NoArgsConstructor
public class Like {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private LocalDateTime createTime;
}