package com.database.backend.mapper;

import com.database.backend.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where username = #{username} and password = #{password}")
    User getByUsernameAndPassword(User user);
}
