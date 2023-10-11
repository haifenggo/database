package com.database.backend.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * @描述
 * @日期 2023/10/11 9:35
 */
@Data
@NoArgsConstructor
public class User {
    private Integer id;

    private Integer userId;

    private String username;

    private String password;

    private LocalDateTime createTime;
}