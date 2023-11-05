package com.database.backend.service;


import com.database.backend.domain.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    User login(User user);

    void deleteUser(Integer userId);

    void updateUser(User user);
    void insertUser(User user);
}
