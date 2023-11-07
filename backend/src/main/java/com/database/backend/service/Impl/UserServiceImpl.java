package com.database.backend.service.Impl;

import com.database.backend.aop.Tracer;
import com.database.backend.domain.entity.User;
import com.database.backend.enumeration.RedisEnum;
import com.database.backend.enumeration.TracerEnum;
import com.database.backend.mapper.LikeMapper;
import com.database.backend.mapper.PostMapper;
import com.database.backend.mapper.UserMapper;
import com.database.backend.service.RedisService;
import com.database.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    PostMapper postMapper;

    @Autowired
    LikeMapper likeMapper;

    @Autowired
    RedisService redisService;


    @Override
    public User login(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer userId) {
        Integer deleteUser = userMapper.deleteUser(userId);
        // todo: 删除用户文章点赞等 x
        Integer deletePost = postMapper.deleteByUserId(userId);

        redisService.deleteByPrefix(RedisEnum.USER);
        redisService.deleteByPrefix(RedisEnum.POST);
        redisService.deleteByPrefix(RedisEnum.LIKE);
    }

    /*
       @Override
    public void createUserAndStudent() {

        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            User user = new User();
            user.setName("kitty");
            user.setSex("women");
            userMapper.createUser(user);

            int a = 10;
            int b = a/0;

            Student student = new Student();
            student.setName("小明");
            student.setScore(85);
            studentMapper.createStudent(student);

            dataSourceTransactionManager.commit(transactionStatus);     //手动提交
        }catch (Exception e) {
            dataSourceTransactionManager.rollback(transactionStatus);       //事务回滚
        }
    }
     */

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void insertUser(User user) {
        user.setCreateTime(LocalDateTime.now());
        userMapper.insertUser(user);
    }

    @Override
    public User selectById(Integer userId) {
        return userMapper.selectById(userId);
    }
}
