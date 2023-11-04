package com.database.backend.service.Impl;

import com.database.backend.context.BaseContext;
import com.database.backend.domain.entity.Like;
import com.database.backend.domain.vo.PostVO;
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

    public void addLike(Integer postId) {
        if(likeMapper.getLikeByPostIdAndUserId(postId, BaseContext.getCurrentId().intValue())!=null)return;

        Like like = Like.builder()
                .userId(BaseContext.getCurrentId().intValue())
                .postId(postId)
                .createTime(LocalDateTime.now())
                .build();

        likeMapper.addLike(like);
    }

    public void deleteLike(Integer postId) {
        if(likeMapper.getLikeByPostIdAndUserId(postId,BaseContext.getCurrentId().intValue())==null)return;

        likeMapper.deleteByPostId(postId);
    }

    public PageResult getLike(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        Page<PostVO> pg=likeMapper.getLike(BaseContext.getCurrentId().intValue());

        return new PageResult(pg.getTotal(),pg.getResult());
    }
}
