package com.database.backend.controller;

import com.database.backend.context.BaseContext;
import com.database.backend.domain.entity.User;
import com.database.backend.domain.queryForm.UserForm;

import com.database.backend.service.Impl.UserService1;
import com.database.backend.service.UserService;
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

    @Autowired
    UserService userService;


    @GetMapping("user/{id}")
    public Result<User> getUser(@PathVariable("id") Integer userId) {
        return Result.success(userService1.getUser(userId));
    }

    @PostMapping("/user")
    public Result<PageResult<User>> getUserList(@RequestBody UserForm userForm){
        return Result.success(userService1.selectUserList(userForm));
    }

    @PostMapping("user/insert")
    public Result insertUser(@RequestBody User user) {
        if(BaseContext.getCurrentId() == null){
            return Result.error("操作失败");
        }
        userService.insertUser(user);
        return Result.success();
    }

    @GetMapping("user/delete/{id}")
    public Result deleteUser(@PathVariable("id") Integer userId) {
        userService.deleteUser(userId);
        // todo: 删除用户文章点赞等
        return Result.success();
    }

    @PostMapping("user/update")
    public Result updateUser(@RequestBody User user) {
//        if(user.getUserId() != BaseContext.getCurrentId()){
//            return Result.error("操作失败");
//        }
        userService.updateUser(user);
        return Result.success();
    }
}
