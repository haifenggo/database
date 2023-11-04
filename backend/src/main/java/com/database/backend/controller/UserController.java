package com.database.backend.controller;

import com.database.backend.domain.entity.User;
import com.database.backend.domain.queryForm.UserForm;

import com.database.backend.service.Impl.UserService1;
import com.database.backend.util.PageResult;
import com.database.backend.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @描述
 * @日期 2023/10/17 12:28
 */
@RestController
public class UserController {

    @Autowired
    UserService1 userService1;

    @GetMapping("/index")
    public String test() {
        return "hello";
    }

    @GetMapping("user/{id}")
    public Result<User> getUser(@PathVariable("id") Integer userId) {
        return Result.success(userService1.getUser(userId));
    }

    @PostMapping("/user")
    public Result<PageResult<User>> getUserList(@RequestBody UserForm userForm){
        return Result.success(userService1.selectUserList(userForm));
    }

    @PostMapping("/user")
    public R<PageResult<User>> getUserList(@RequestBody UserForm userForm){
        return R.success(userService.selectUserList(userForm));
    }
}
