package com.database.backend.controller;

import com.database.backend.domain.entity.User;
import com.database.backend.domain.queryForm.UserForm;
import com.database.backend.service.UserService;
import com.database.backend.util.PageResult;
import com.database.backend.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/user")
    public R<PageResult<User>> getUserList(@RequestBody UserForm userForm){
        return R.success(userService.selectUserList(userForm));
    }
}
