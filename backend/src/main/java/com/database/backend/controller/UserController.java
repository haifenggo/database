package com.database.backend.controller;

import com.database.backend.entity.User;
import com.database.backend.service.UserService;
import com.database.backend.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述
 * @日期 2023/10/17 12:28
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String test() {
        return "hello";
    }

    @GetMapping("user/{id}")
    public R<User> getUser(@PathVariable("id") Integer userId) {
        return R.success(userService.getUser(userId));
    }
}
