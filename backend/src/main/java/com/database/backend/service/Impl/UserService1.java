package com.database.backend.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.database.backend.aop.Tracer;
import com.database.backend.dao.UserDao;
import com.database.backend.domain.entity.User;
import com.database.backend.domain.queryForm.UserForm;
import com.database.backend.enumeration.RedisEnum;
import com.database.backend.enumeration.TracerEnum;
import com.database.backend.service.RedisService;
import com.database.backend.util.PageResult;
import com.database.backend.util.PageUtil;
import com.database.backend.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @描述
 * @日期 2023/10/24 8:36
 */
@Service
public class UserService1 {
    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;
    public User getUser(Integer userId) {
        String redisKey = redisService.generateRedisKey(RedisEnum.USER, userId);
        if (!redisService.hasKey(redisKey)) {
            User user = userDao.selectUser(userId);
            redisService.set(redisKey, user, TimeUtil.TWO_HOUR);
        }
        return redisService.getObject(redisKey, User.class);
    }
    public PageResult<User> selectUserList(UserForm userForm) {
        String redisKey = redisService.generateRedisKey(RedisEnum.USER, userForm);
        Object redisResult = redisService.get(redisKey);
        if (redisResult != null) {
            // 如果数据存在于Redis中，则进行类型转换并直接返回
            return JSON.parseObject((String) redisResult, new TypeReference<>() {
            });
        }
        // 查询总记录数
        long total = userDao.countUserList();
        // 查询用户列表
        List<User> userList = userDao.selectUserList(userForm);
        PageResult<User> pageResult = PageUtil.convert2PageResult(userForm, total, userList);
        redisService.set(redisKey, pageResult, TimeUtil.TWO_MINUTE);
        return pageResult;
    }
}
