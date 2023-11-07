package com.database.backend.mapper;

import com.database.backend.aop.Tracer;
import com.database.backend.domain.entity.User;
import com.database.backend.enumeration.TracerEnum;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Tracer(type = TracerEnum.SELECT)
    @Select("select * from t_user where username = #{username} and password = #{password}")
    User getByUsernameAndPassword(User user);

    @Tracer(type = TracerEnum.DELETE)
    @Delete("delete from t_user where user_id=#{userId}")
    int deleteUser(Integer userId);

    @Tracer(type = TracerEnum.UPDATE)
    @Update("update t_user set username=#{username},password =#{password} where user_id=#{userId}")
    void updateUser(User user);

    @Tracer(type = TracerEnum.INSERT)
    @Insert("insert into t_user (username, password, create_time) values (#{username}, #{password}, #{createTime})")
    void insertUser(User user);

    @Select("select * from t_user where user_id = #{userId}")
    User selectById(Integer userId);
}
