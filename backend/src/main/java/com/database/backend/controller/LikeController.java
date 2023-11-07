package com.database.backend.controller;

import com.database.backend.aop.Tracer;
import com.database.backend.context.BaseContext;
import com.database.backend.domain.entity.Like;
import com.database.backend.domain.queryForm.LikeForm;
import com.database.backend.domain.queryForm.PostForm;
import com.database.backend.domain.vo.PostVO;
import com.database.backend.enumeration.TracerEnum;
import com.database.backend.mapper.LikeMapper;
import com.database.backend.service.LikeService;
import com.database.backend.util.PageResult;
import com.database.backend.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController

@Slf4j
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/like")
    @Tracer(type = TracerEnum.REQUEST)
    public Result<PageResult<Like>> getLikeList(@RequestBody LikeForm likeForm) {
        return Result.success(likeService.getLikeList(likeForm));
    }

    @GetMapping("/like/delete/{id}")
    @Tracer(type = TracerEnum.REQUEST)
    public Result deleteById(@PathVariable("id") Integer id) {
        likeService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/like/post/{id}")
    @Tracer(type = TracerEnum.REQUEST)
    public Result likePost(@PathVariable("id") Integer id) {
        Boolean liked = likeService.like(BaseContext.getCurrentId(), id);
        return Result.success(liked);
    }
}
