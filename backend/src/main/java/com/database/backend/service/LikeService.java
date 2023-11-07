package com.database.backend.service;


import com.database.backend.domain.entity.Like;
import com.database.backend.domain.queryForm.LikeForm;
import com.database.backend.util.PageResult;
import org.springframework.web.bind.annotation.PathVariable;

public interface LikeService {

    PageResult<Like> getLikeList(LikeForm likeForm);


    void deleteById(Integer id);

    Boolean liked(Integer userId, Integer postId);

    Boolean like(Integer userId, Integer postId);

    Integer selectPostLikeCount(Integer postId);
}
