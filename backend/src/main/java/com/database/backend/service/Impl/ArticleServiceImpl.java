package com.database.backend.service.Impl;


import com.database.backend.context.BaseContext;
import com.database.backend.domain.entity.Post;
import com.database.backend.domain.vo.PostVO;
import com.database.backend.mapper.ArticleMapper;
import com.database.backend.mapper.LikeMapper;
import com.database.backend.service.ArticleService;
import com.database.backend.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private LikeMapper likeMapper;

    public PageResult page(Integer page, Integer pageSize, Integer user_id, String title) {
        PageHelper.startPage(page,pageSize);

        List<PostVO> list= articleMapper.pageQuery(user_id,title);
        Page<PostVO> pg=(Page<PostVO>)list;

        return new PageResult(pg.getTotal(),pg.getResult());
    }

    public Post getByPostId(Integer postId) {
        return articleMapper.getById(postId);
    }

    public void updatePost(Post post) {
        articleMapper.updatePost(post);
    }

    @Transactional(rollbackFor=Exception.class)
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            likeMapper.deleteByPostId(id);
            articleMapper.deleteByPostId(id);
        }
    }

    public void addPost(Post post) {
        post.setCreateTime(LocalDateTime.now());
        //todo  增加user_id
        post.setUserId(BaseContext.getCurrentId().intValue());
        articleMapper.addPost(post);
    }

    public PageResult pagePosted(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        List<PostVO> list= articleMapper.pagePostedQuery(BaseContext.getCurrentId().intValue());
        Page<PostVO> pg=(Page<PostVO>)list;

        return new PageResult(pg.getTotal(),pg.getResult());

    }
}
