package com.database.backend.mapper;


import com.database.backend.domain.entity.Post;
import com.database.backend.domain.queryForm.PostForm;
import com.database.backend.domain.vo.PostDetailVO;
import com.database.backend.domain.vo.PostVO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {

    @Select("<script>" +
            "SELECT post_id, user_id, title, create_time FROM t_post "+

            "ORDER BY "+
            "<foreach collection='postForm.sortItemList' item='item' separator=','>"+
            "${item.column} ${item.isAsc ? 'ASC' : 'DESC'}"+
            "</foreach>"+
            "LIMIT #{postForm.pageSize} OFFSET ${postForm.pageSize * (postForm.pageNum - 1)}"+
            "<where>"+
            "<if test='postForm.userId != null'>"+
            "AND user_id = #{postForm.userId}"+
            "</if>"+
            "</where>"+


            "</script>")
    List<PostVO> getPostList( @Param("postForm") PostForm postForm);
// todo: 连表查询，根据username查
    /**
     "<if test='username != null'>"+
     "AND username = #{postForm.username}"+
     "</if>"+
     */
// todo: 连表查询，根据username查
    @Select("<script>" +
            "SELECT COUNT(*) FROM t_post "+
            "<where>"+
            "<if test=\"postForm.userId != null\">"+
            "AND user_id = #{postForm.userId}"+
            "</if>"+
            "</where>"+
            "</script>")
    Long getPostListTotal( @Param("postForm") PostForm postForm);


    @Select("select * from t_post where post_id = #{postId}")
    PostDetailVO getPostDetail(Integer postId);

    @Delete("delete from t_post where post_id=#{id}")
    void deleteByPostId(Integer id);

    @Update("update t_post set title=#{title},content =#{content} where post_id =#{postId}")
    void updatePost(Post post);

    @Insert("insert into t_post (user_id, title, content, create_time) values (#{userId}, #{title},#{content},#{createTime})")
    void addPost(Post post);
}
