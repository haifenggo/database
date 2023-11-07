package com.database.backend.service.Impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.database.backend.aop.Tracer;
import com.database.backend.context.BaseContext;
import com.database.backend.domain.entity.Post;
import com.database.backend.domain.queryForm.PostForm;
import com.database.backend.domain.vo.PostDetailVO;
import com.database.backend.domain.vo.PostVO;
import com.database.backend.enumeration.RedisEnum;
import com.database.backend.enumeration.TracerEnum;
import com.database.backend.mapper.PostMapper;
import com.database.backend.service.PostService;
import com.database.backend.service.RedisService;
import com.database.backend.util.PageResult;
import com.database.backend.util.PageUtil;
import com.database.backend.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    RedisService redisService;

    @Override
    public PageResult<PostVO> getPostList(PostForm postForm) {
        String redisKey = redisService.generateRedisKey(RedisEnum.POST, postForm);
        Object redisResult = redisService.get(redisKey);
        if (redisResult != null) {
            // 如果数据存在于Redis中，则进行类型转换并直接返回
            return JSON.parseObject((String) redisResult, new TypeReference<>() {
            });
        }
        List<PostVO> postList = postMapper.getPostList(postForm);
        Long total = postMapper.getPostListTotal(postForm);
        PageResult<PostVO> postVOPageResult = PageUtil.convert2PageResult(postForm, total, postList);
        redisService.set(redisKey, postVOPageResult, TimeUtil.TWO_MINUTE);
        return postVOPageResult;
    }

    @Override
    public PostDetailVO getPostDetail(Integer postId) {
        PostDetailVO postDetail = postMapper.getPostDetail(postId);
        // todo: 补充liked和username x
        return postDetail;
    }

    @Override
    public void deleteByPostId(Integer postId) {
        postMapper.deleteByPostId(postId);
        // todo: 删除点赞 x
    }

    @Override
    public void updatePost(Post post) {
        postMapper.updatePost(post);
    }

    @Override
    public void addPost(Post post) {
        post.setCreateTime(LocalDateTime.now());
        post.setUserId(BaseContext.getCurrentId());
        postMapper.addPost(post);
    }
}
