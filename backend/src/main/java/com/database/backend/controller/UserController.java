package com.database.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述
 * @日期 2023/10/17 12:28
 */
@RestController
public class UserController {

    @GetMapping("/index")
    public String test(){
        return "hello";
    }
}
