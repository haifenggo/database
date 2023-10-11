package com.database.backend;

import com.database.backend.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void testUserCount() {
        System.out.println(userDao.getUserCount());
    }



}
