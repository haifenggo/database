package com.database.backend.mapper;


import com.database.backend.domain.entity.Post;
import com.database.backend.domain.vo.PostVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    public List<PostVO> pageQuery(Integer user_id, String title);

    @Select("select * from t_post where post_id=#{postId}")
    Post getById(Integer postId);

    @Update("update t_post set title=#{title},content =#{content} where post_id =#{postId}")
    void updatePost(Post post);

    @Delete("delete from t_post where post_id=#{id}")
    void deleteByPostId(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "postId")
    @Insert("insert into t_post (user_id, title, content, create_time) values (#{userId},#{title},#{content},#{createTime})")
    void addPost(Post post);

    @Select("select * from t_post where user_id=#{userId}")
    List<PostVO> pagePostedQuery(Integer userId);
}
