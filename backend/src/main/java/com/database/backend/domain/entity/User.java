package com.database.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
/**
 * @描述
 * @日期 2023/10/11 9:35
 */
@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer userId;

    private String username;

    private String password;

    private LocalDateTime createTime;
}
