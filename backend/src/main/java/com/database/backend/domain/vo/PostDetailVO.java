package com.database.backend.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @描述
 * @日期 2023/11/5 16:56
 */
@Data
public class PostDetailVO {
    private Integer postId;
    private Integer userId;
    private String username;
    private String content;
    private String title;
    private Boolean liked;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
