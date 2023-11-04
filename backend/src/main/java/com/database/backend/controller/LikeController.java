package com.database.backend.controller;

import com.database.backend.context.BaseContext;
import com.database.backend.mapper.LikeMapper;
import com.database.backend.service.LikeService;
import com.database.backend.anno.Log;
import com.database.backend.util.PageResult;
import com.database.backend.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@Slf4j
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Log
    @GetMapping("/page")
    public Result<PageResult> getLikeArticle(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        log.info("分页查询, 参数: {},{},{},{},{}", page, pageSize);
        PageResult pageResult = likeService.getLike(page, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 查看该文章是否已点赞？利用redis
     * @param postId
     * @return
     */
    @GetMapping
    public Result<Integer> judgeLikeOrNot(Integer postId) {
        log.info("判断文章编号为{}赞了没有", postId);
        //构造redis中的key，规则：userId_postId_
        Integer userId= BaseContext.getCurrentId().intValue();
        String key = userId+"_"+postId+"_";
        //查询redis中是否已经存在对应文章赞关系
        //存在
        Integer status=(Integer) redisTemplate.opsForValue().get(key);
        if(status!=null){
            return Result.success(status);
        }
        //不存在
        status=likeMapper.getLikeByPostIdAndUserId(postId,userId)==null?0:1;
        redisTemplate.opsForValue().set(key,status);

//        if(likeMapper.getLikeByPostIdAndUserId(postId,userId)==null)return Result.success(0);
        return Result.success(status);
    }

    @Log
    @PostMapping("/{postId}")
    public Result addLike(@PathVariable Integer postId) {
        log.info("给文章编号{}点赞", postId);
        //构造redis中的key，规则：userId_postId_
        Integer userId=BaseContext.getCurrentId().intValue();
        String key = userId+"_"+postId+"_";

        redisTemplate.delete(key);

        redisTemplate.opsForValue().set(key,1);

        likeService.addLike(postId);
        return Result.success();
    }

    @Log
    @DeleteMapping("/{postId}")
    public Result deleteLike(@PathVariable Integer postId) {
        log.info("给文章编号{}取消赞", postId);

        //构造redis中的key，规则：userId_postId_
        Integer userId=BaseContext.getCurrentId().intValue();
        String key = userId+"_"+postId+"_";

        redisTemplate.delete(key);

        redisTemplate.opsForValue().set(key,0);

        likeService.deleteLike(postId);
        return Result.success();
    }
}
