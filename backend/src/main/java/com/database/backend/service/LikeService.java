package com.database.backend.service;


import com.database.backend.util.PageResult;

public interface LikeService {

    void addLike(Integer postId);

    void deleteLike(Integer postId);

    PageResult getLike(Integer page, Integer pageSize);
}
