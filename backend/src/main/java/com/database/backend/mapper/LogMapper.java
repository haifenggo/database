package com.database.backend.mapper;

import com.database.backend.domain.entity.DataTracer;
import com.database.backend.domain.entity.LoginLog;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LogMapper {

    @Insert("insert into t_login_log (user_id, username, login_result, create_time) values (#{userId},#{username}, #{loginResult},#{createTime})")
    void addLoginLog(LoginLog loginLog);

    @Select("select * from t_login_log")
    Page<LoginLog> getLoginLog();

    @Select("select * from t_data_tracer")
    Page<DataTracer> TracerLog();

    @Insert("insert into t_data_tracer (user_id, content, create_time) VALUES (#{userId},#{content},#{createTime})")
    void addTracerLog(DataTracer dataTracer);
}
