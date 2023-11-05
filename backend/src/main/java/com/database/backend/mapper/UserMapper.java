package com.database.backend.mapper;

import com.database.backend.domain.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where username = #{username} and password = #{password}")
    User getByUsernameAndPassword(User user);

    @Delete("delete from t_user where user_id=#{userId}")
    int deleteUser(Integer userId);

    @Update("update t_user set username=#{username},password =#{password} where user_id=#{userId}")
    void updateUser(User user);

    @Insert("insert into t_user (username, password, create_time) values (#{username}, #{password}, #{createTime})")
    void insertUser(User user);
}
