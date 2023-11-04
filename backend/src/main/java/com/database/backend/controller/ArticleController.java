package com.database.backend.controller;

import com.database.backend.domain.entity.Post;
import com.database.backend.service.ArticleService;
import com.database.backend.anno.Log;
import com.database.backend.util.PageResult;
import com.database.backend.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 条件分页查询
     *
     * @param page
     * @param pageSize
     * @param userId
     * @return
     */
    @Log
    @GetMapping("/page")
    public Result<PageResult> page(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   Integer userId,
                                   String title
    ) {
        log.info("分页查询, 参数: {},{},{},{}", page, pageSize, userId, title);
        PageResult pageResult = articleService.page(page, pageSize, userId, title);
        return Result.success(pageResult);
    }

    @Log
    @GetMapping("/posted")
    public Result<PageResult> pagePosted(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        log.info("分页查询, 参数: {},{},{},{},{}", page, pageSize);
        PageResult pageResult = articleService.pagePosted(page, pageSize);
        return Result.success(pageResult);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Post post) {
        log.info("修改文章：{}", post);
        articleService.updatePost(post);

        return Result.success();
    }

    /**
     * 根据postId查询文章，阅读（可以实现redis操作）
     *
     * @param postId
     * @return
     */
    @Log
    @GetMapping("/{postId}")
    public Result<Post> getByPostId(@PathVariable Integer postId) {
        log.info("postId:{}", postId);

        //构造redis中的key，规则：article_postId
        String key = "article_" + postId;
        //查询redis中是否已经存在该文章内容相关数据
        Post post = (Post) redisTemplate.opsForValue().get(key);
        //若有，直接return
        if(post!=null)return Result.success(post);
        //否则反之
        post=articleService.getByPostId(postId);
        redisTemplate.opsForValue().set(key, post);

        return Result.success(post);
    }

    /**
     * 发表自己的文章，利用redis新增文章时把文章保存到内存中去
     * @param post
     * @return
     */
    @Log
    @PostMapping
    public Result addPost(@RequestBody Post post) {
        log.info("增加文章{}", post);

        articleService.addPost(post);//java中对象传递其实是引用传递，传递进去时postId为null
        Integer postId=post.getPostId();
        //构造redis中的key，规则：article_postId
        String key = "article_" + postId;
        redisTemplate.opsForValue().set(key, post);

        return Result.success();
    }

    /**
     * 删除自己发表的文章，不要忘记redis中清理文章缓存,不用管点赞内存情况
     * @param ids
     * @return
     */
    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("文章批量删除：{}", ids);
        articleService.deleteBatch(ids);

        for (Integer id : ids) {
            redisTemplate.delete("article_"+ids);
        }

        return Result.success();
    }
}
