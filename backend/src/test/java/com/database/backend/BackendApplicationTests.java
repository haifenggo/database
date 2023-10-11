package com.database.backend;

import com.database.backend.dao.UserDao;
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

    @Test
    void testUserCount() {
        System.out.println(userDao.getUserCount());
    }

    @Test
    void testRedis(){
        Set keys = redisTemplate.keys("*");
        System.out.println(keys);
    }



}
