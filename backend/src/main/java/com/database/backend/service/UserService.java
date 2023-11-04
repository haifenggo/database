package com.database.backend.service;

import com.database.backend.domain.entity.User;

public interface UserService {
    User login(User user);
}
