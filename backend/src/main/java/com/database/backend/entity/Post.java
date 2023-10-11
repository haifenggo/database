package com.database.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @描述
 * @日期 2023/10/11 9:58
 */
@Data
@NoArgsConstructor
public class Post {
    private Integer id;
    private Integer postId;
    private String title;
    private String content;
    private LocalDateTime createTime;
}
