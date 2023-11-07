package com.database.backend.service.Impl;

import com.database.backend.aop.Tracer;
import com.database.backend.context.BaseContext;
import com.database.backend.domain.entity.Like;
import com.database.backend.domain.queryForm.LikeForm;
import com.database.backend.domain.vo.PostVO;
import com.database.backend.enumeration.TracerEnum;
import com.database.backend.mapper.LikeMapper;
import com.database.backend.service.LikeService;
import com.database.backend.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeMapper likeMapper;

    @Override
    public PageResult<Like> getLikeList(LikeForm likeForm) {
        PageHelper.startPage(likeForm.getPageNum(), likeForm.getPageSize());
        Page<Like> likeList = likeMapper.getLikeList(likeForm);
        PageResult<Like> pageResult = new PageResult<>(likeList.getTotal(), likeList.getResult());
        return pageResult;
    }

    @Override
    public void deleteById(Integer id) {
        likeMapper.deleteById(id);
    }

    @Override
    public Boolean liked(Integer userId, Integer postId) {
        Like like = likeMapper.selectOne(userId, postId);
        return like != null;
    }

    @Override
    public Boolean like(Integer userId, Integer postId) {
        Boolean liked = this.liked(userId, postId);
        if (liked) {
            likeMapper.deleteLike(userId, postId);
        } else {
            likeMapper.insertLike(userId, postId, LocalDateTime.now());
        }
        return liked;
    }

    @Override
    public Integer selectPostLikeCount(Integer postId) {
        return likeMapper.selectPostLikeCount(postId);
    }
}
