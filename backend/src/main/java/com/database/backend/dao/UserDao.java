package com.database.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @描述
 * @日期 2023/10/11 10:16
 */
@Repository
public class UserDao {
    @Autowired
    JdbcTemplate template;

    public int getUserCount(){
        String sql = "select count(1) from t_user";
        Integer count = template.queryForObject(sql, Integer.class);

        return count;
    }
}
