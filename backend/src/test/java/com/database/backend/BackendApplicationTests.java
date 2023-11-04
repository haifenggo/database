package com.database.backend;

import com.database.backend.dao.UserDao;
import com.database.backend.domain.entity.User;
import com.database.backend.service.RedisService;
import com.database.backend.service.Impl.UserService1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    UserDao userDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserService1 userService1;

    @Autowired
    RedisService redisService;

    @Test
    void testUser(){
        User user = userService1.getUser(201);
        System.out.println(user);
    }



    @Test
    void testUserCount() {
//        System.out.println(userDao.getUserCount());
//        System.out.println(userDao.getUserCount());
//        System.out.println(userDao.getUserCount());
//        System.out.println(userDao.getUserCount());
    }

    @Test
    void testRedis() {
        Set keys = redisTemplate.keys("*");
        System.out.println(keys);
        redisService.deleteByPrefix("user");
        System.out.println(redisTemplate.keys("user*"));
    }

    @Test
    void testTransactional() {
        userDao.testTransactional();
        userDao.testTransactionTemplate();
        userDao.testTransaction();
    }

}
