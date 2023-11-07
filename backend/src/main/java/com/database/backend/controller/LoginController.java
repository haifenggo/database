package com.database.backend.controller;

import com.database.backend.aop.Tracer;
import com.database.backend.context.BaseContext;
import com.database.backend.domain.entity.LoginLog;
import com.database.backend.domain.entity.User;
import com.database.backend.enumeration.LoginEnum;
import com.database.backend.enumeration.TracerEnum;
import com.database.backend.service.LoginLogService;
import com.database.backend.service.UserService;
import com.database.backend.util.JwtUtils;
import com.database.backend.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
public class LoginController {

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @Tracer(type = TracerEnum.REQUEST)
    public Result login(@RequestBody User user, HttpServletRequest request) {
        log.info("{}登陆了", user);

        User u = userService.login(user);

        if (u != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", u.getUserId());
            claims.put("username", u.getUsername());

            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的信息


            LoginLog loginLog = LoginLog.builder()
                    .userId(u.getUserId())
                    .username(u.getUsername())
                    .loginResult(LoginEnum.SUCCESS.getValue())
                    .createTime(LocalDateTime.now())
                    .build();
            loginLogService.insertLog(loginLog);
            BaseContext.setCurrentId(u.getUserId());
            return Result.success(jwt);
        }


        LoginLog loginLog = LoginLog.builder()
                .userId(0)
                .username(user.getUsername())
                .loginIp(String.valueOf(request.getRemoteAddr()))
                .loginResult(LoginEnum.FAILURE.getValue())
                .createTime(LocalDateTime.now())
                .build();
        loginLogService.insertLog(loginLog);
        return Result.error("用户名或密码错误！");
    }
}
