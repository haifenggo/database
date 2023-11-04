package com.database.backend.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @描述
 * @日期 2023/10/11 9:58
 */
@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer postId;
    private Integer userId;

    private String title;
    private String content;
    private LocalDateTime createTime;
}
