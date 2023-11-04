package com.database.backend.domain.entity;


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
    private LocalDateTime createTime;
}
