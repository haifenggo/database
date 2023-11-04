package com.database.backend.mapper;

import com.database.backend.domain.entity.Like;
import com.database.backend.domain.vo.PostVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LikeMapper {

    @Delete("delete from t_like where post_id=#{id}")
    void deleteByPostId(Integer id);

    @Insert("insert into t_like (user_id, post_id, create_time) values (#{userId},#{postId},#{createTime})")
    void addLike(Like like);

    @Select("select like_id from t_like where user_id=#{userId} and post_id=#{postId}")
    Integer getLikeByPostIdAndUserId(Integer postId,Integer userId);

    @Select("select * from t_post where post_id in(select post_id from t_like where user_id =#{userId})")
    Page<PostVO> getLike(Integer userId);
}
