package com.database.backend.mapper;


import com.database.backend.aop.Tracer;
import com.database.backend.domain.entity.Post;
import com.database.backend.domain.queryForm.PostForm;
import com.database.backend.domain.vo.PostDetailVO;
import com.database.backend.domain.vo.PostVO;
import com.database.backend.enumeration.TracerEnum;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {
    @Tracer(type = TracerEnum.SELECT)
    @Select("<script>" +
            "SELECT post_id, user_id, title, create_time FROM t_post " +

            "<where>" +
            "<if test='postForm.userId != null'>" +
            "AND user_id = #{postForm.userId}" +
            "</if>" +
            "</where>" +

            "ORDER BY " +
            "<foreach collection='postForm.sortItemList' item='item' separator=','>" +
            "${item.column} ${item.isAsc ? 'ASC' : 'DESC'}" +
            "</foreach>" +
            "LIMIT #{postForm.pageSize} OFFSET ${postForm.pageSize * (postForm.pageNum - 1)}" +


            "</script>")
    List<PostVO> getPostList(@Param("postForm") PostForm postForm);
// todo: 连表查询，根据username查

    /**
     * "<if test='username != null'>"+
     * "AND username = #{postForm.username}"+
     * "</if>"+
     */
// todo: 连表查询，根据username查
    @Tracer(type = TracerEnum.SELECT)
    @Select("<script>" +
            "SELECT COUNT(*) FROM t_post " +
            "<where>" +
            "<if test=\"postForm.userId != null\">" +
            "AND user_id = #{postForm.userId}" +
            "</if>" +
            "</where>" +
            "</script>")
    Long getPostListTotal(@Param("postForm") PostForm postForm);



    @Tracer(type = TracerEnum.SELECT)
    @Select("select p.*, u.username from t_post as p " +
            "left join t_user as u on p.user_id = u.user_id " +
            "where p.post_id = #{postId}")
    PostDetailVO getPostDetail(Integer postId);

    @Tracer(type = TracerEnum.DELETE)
    @Delete("delete p, l from t_post as p " +
            "left join t_like as l on p.post_id = l.post_id " +
            " where p.post_id=#{id}")
    void deleteByPostId(Integer id);

    @Tracer(type = TracerEnum.DELETE)
    @Delete("delete p, l from t_post as p " +
            "left join t_like as l on p.post_id = l.post_id " +
            " where p.user_id=#{id}")
    Integer deleteByUserId(Integer id);

    @Tracer(type = TracerEnum.UPDATE)
    @Update("update t_post set title=#{title},content =#{content} where post_id =#{postId}")
    void updatePost(Post post);

    @Tracer(type = TracerEnum.INSERT)
    @Insert("insert into t_post (user_id, title, content, create_time) values (#{userId}, #{title},#{content},#{createTime})")
    void addPost(Post post);
}
