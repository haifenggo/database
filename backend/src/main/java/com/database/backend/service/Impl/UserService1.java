package com.database.backend.service.Impl;

import com.database.backend.dao.UserDao;
import com.database.backend.domain.entity.User;
import com.database.backend.domain.queryForm.UserForm;
import com.database.backend.enumeration.RedisEnum;
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
        // 查询总记录数
        long total = userDao.countUserList();
        // 查询用户列表
        List<User> userList = userDao.selectUserList(userForm);

        return PageUtil.convert2PageResult(userForm, total,userList);
    }
}
