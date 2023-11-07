package com.database.backend.mapper;

import com.database.backend.aop.Tracer;
import com.database.backend.domain.entity.Like;
import com.database.backend.domain.queryForm.LikeForm;
import com.database.backend.domain.vo.PostVO;
import com.database.backend.enumeration.TracerEnum;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;

@Mapper
public interface LikeMapper {

    @Tracer(type = TracerEnum.SELECT)
    @Select("<script>" +
            "select * from t_like " +
            "ORDER BY " +
            "<foreach collection='likeForm.sortItemList' item='item' separator=','>" +
            "${item.column} ${item.isAsc ? 'ASC' : 'DESC'}" +
            "</foreach>" +
            "</script>"
    )
    Page<Like> getLikeList(@Param("likeForm") LikeForm likeForm);

    @Tracer(type = TracerEnum.DELETE)
    @Delete("delete from t_like where id=#{id}")
    void deleteById(Integer id);

    @Tracer(type = TracerEnum.SELECT)
    @Select("select * from t_like where user_id = #{userId} and post_id = #{postId}")
    Like selectOne(Integer userId, Integer postId);

    @Tracer(type = TracerEnum.INSERT)
    @Insert("insert into t_like (user_id, post_id, create_time) values (#{userId},#{postId},#{time})")
    void insertLike(Integer userId, Integer postId, LocalDateTime time);

    @Tracer(type = TracerEnum.DELETE)
    @Delete("delete from t_like where user_id = #{userId} and post_id = #{postId}")
    void deleteLike(Integer userId, Integer postId);

    @Tracer(type = TracerEnum.SELECT)
    @Select("select count(*) from t_like where  post_id = #{postId}")
    Integer selectPostLikeCount(Integer postId);
}
