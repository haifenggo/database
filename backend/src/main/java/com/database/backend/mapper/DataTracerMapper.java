package com.database.backend.mapper;

import com.database.backend.aop.Tracer;
import com.database.backend.domain.entity.DataTracer;
import com.database.backend.domain.queryForm.TracerForm;
import com.database.backend.enumeration.TracerEnum;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @描述
 * @日期 2023/11/6 10:34
 */
@Mapper
public interface DataTracerMapper {

    @Tracer(type = TracerEnum.SELECT)
    @Select("<script>" +
            "select * from t_data_tracer " +
            "ORDER BY " +
            "<foreach collection='pageParam.sortItemList' item='item' separator=','>" +
            "${item.column} ${item.isAsc ? 'ASC' : 'DESC'}" +
            "</foreach>" +
            "</script>"
    )
    Page<DataTracer> getTracerList(@Param("pageParam") TracerForm pageParam);

    @Insert("insert into t_data_tracer (user_id, type, content, create_time) values (#{userId},#{type},#{content},#{createTime})")
    void insertTracer(DataTracer dataTracer);
}
