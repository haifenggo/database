package com.database.backend.mapper;

import com.database.backend.aop.Tracer;
import com.database.backend.domain.entity.Like;
import com.database.backend.domain.entity.LoginLog;
import com.database.backend.domain.queryForm.LikeForm;
import com.database.backend.domain.queryForm.LoginLogForm;
import com.database.backend.enumeration.TracerEnum;
import com.database.backend.util.PageParam;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @描述
 * @日期 2023/11/6 10:27
 */
@Mapper
public interface LoginLogMapper {

    @Tracer(type = TracerEnum.SELECT)
    @Select("<script>" +
            "select * from t_login_log " +
            "ORDER BY " +
            "<foreach collection='pageParam.sortItemList' item='item' separator=','>" +
            "${item.column} ${item.isAsc ? 'ASC' : 'DESC'}" +
            "</foreach>" +
            "</script>"
    )
    Page<LoginLog> getLoginLogList(@Param("pageParam") LoginLogForm pageParam);
}
