package com.database.backend.controller;

import com.database.backend.aop.Tracer;
import com.database.backend.context.BaseContext;
import com.database.backend.domain.entity.User;
import com.database.backend.domain.queryForm.UserForm;

import com.database.backend.enumeration.TracerEnum;
import com.database.backend.service.Impl.UserService1;
import com.database.backend.service.UserService;
import com.database.backend.util.PageResult;
import com.database.backend.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    @Tracer(type = TracerEnum.URI)
    public Result<User> getUser(@PathVariable("id") Integer userId) {
        return Result.success(userService1.getUser(userId));
    }

    @PostMapping("/user")
    @Tracer(type = TracerEnum.URI)
    public Result<PageResult<User>> getUserList(@RequestBody UserForm userForm){
        return Result.success(userService1.selectUserList(userForm));
    }

    @PostMapping("user/insert")
    @Tracer(type = TracerEnum.URI)
    public Result insertUser(@RequestBody User user) {
        if(BaseContext.getCurrentId() == null){
            return Result.error("操作失败");
        }
        userService.insertUser(user);
        return Result.success();
    }

    @GetMapping("user/delete/{id}")
    @Tracer(type = TracerEnum.URI)
    public Result deleteUser(@PathVariable("id") Integer userId) {
        userService.deleteUser(userId);

        return Result.success();
    }

    @PostMapping("user/update")
    @Tracer(type = TracerEnum.URI)
    public Result updateUser(@RequestBody User user) {
//        if(user.getUserId() != BaseContext.getCurrentId()){
//            return Result.error("操作失败");
//        }
        userService.updateUser(user);
        return Result.success();
    }
}
