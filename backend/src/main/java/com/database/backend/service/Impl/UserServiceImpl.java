package com.database.backend.service.Impl;

import com.database.backend.domain.entity.User;
import com.database.backend.mapper.UserMapper;
import com.database.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User login(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }
}
