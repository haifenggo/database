package com.database.backend.controller;

import com.database.backend.domain.entity.Post;
import com.database.backend.domain.queryForm.PostForm;
import com.database.backend.domain.vo.PostDetailVO;
import com.database.backend.domain.vo.PostVO;
import com.database.backend.service.PostService;
import com.database.backend.util.PageResult;
import com.database.backend.util.Result;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("/post")
    public Result<PageResult<PostVO>> getPostList(@RequestBody PostForm postForm) {
        return Result.success(postService.getPostList(postForm));
    }

    @GetMapping("/post/{postId}")
    public Result<PostDetailVO> getPostDetail(@PathVariable("postId") Integer postId){
        // todo: 补充liked和username
        PostDetailVO postDetail = postService.getPostDetail(postId);
        return Result.success(postDetail);
    }

    @GetMapping("/post/delete/{postId}")
    public Result deleteById(@PathVariable("postId") Integer postId) {
        // todo: 删除点赞
        postService.deleteByPostId(postId);

        return Result.success();
    }

    @PostMapping("/post/update")
    public Result updatePost(@RequestBody Post post) {
        postService.updatePost(post);
        return Result.success();
    }

    @PostMapping("/post/insert")
    public Result addPost(@RequestBody Post post) {
        postService.addPost(post);
        return Result.success();
    }

}
