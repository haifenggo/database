package com.database.backend.service;


import com.database.backend.domain.entity.Post;
import com.database.backend.domain.queryForm.PostForm;
import com.database.backend.domain.vo.PostDetailVO;
import com.database.backend.domain.vo.PostVO;
import com.database.backend.util.PageResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface PostService {

    PageResult<PostVO> getPostList(PostForm postForm);

    PostDetailVO getPostDetail(Integer postId);

    void deleteByPostId(Integer postId);

    void updatePost(Post post);

    void addPost(Post post);
}
