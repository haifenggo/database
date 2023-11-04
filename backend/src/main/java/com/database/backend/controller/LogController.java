package com.database.backend.controller;

import com.database.backend.service.LogService;
import com.database.backend.util.PageResult;
import com.database.backend.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {
    @Autowired
    private LogService logService;


    @GetMapping("/LoginLog/page")
    public Result<PageResult> getLoginLog(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        log.info("分页查询, 参数: {},{},{},{},{}", page, pageSize);
        PageResult pageResult = logService.getLoginLog(page, pageSize);
        return Result.success(pageResult);
    }

    @GetMapping("/TracerLog/page")
    public Result<PageResult> getTracerLog(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        log.info("分页查询, 参数: {},{},{},{},{}", page, pageSize);
        PageResult pageResult = logService.TracerLog(page, pageSize);
        return Result.success(pageResult);
    }
}
