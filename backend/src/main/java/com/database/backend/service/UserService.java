package com.database.backend.service;

import com.database.backend.dao.UserDao;
import com.database.backend.entity.User;
import com.database.backend.enumeration.RedisEnum;
import com.database.backend.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @描述
 * @日期 2023/10/24 8:36
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;

    public User getUser(Integer userId){
        String redisKey = redisService.generateRedisKey(RedisEnum.USER, userId);
        if(!redisService.hasKey(redisKey)){
            User user = userDao.selectUser(userId);
            redisService.set(redisKey, user, TimeUtil.TWO_HOUR);
        }
        return redisService.getObject(redisKey, User.class);
    }
}
