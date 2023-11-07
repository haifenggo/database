package com.database.backend.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @描述
 * @日期 2023/10/11 10:01
 */
@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Like {
    private Integer id;
    private Integer userId;
    private Integer postId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}