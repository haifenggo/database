package com.database.backend.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @描述
 * @日期 2023/10/11 9:54
 */
@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LoginLog {
    private Integer loginLogId;
    private Integer userId;
    private String username;
    private String loginIp;
    private Integer loginResult;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
