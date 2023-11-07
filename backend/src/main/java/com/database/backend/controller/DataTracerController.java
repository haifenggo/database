package com.database.backend.controller;

import com.database.backend.aop.Tracer;
import com.database.backend.domain.entity.DataTracer;
import com.database.backend.domain.entity.LoginLog;
import com.database.backend.domain.queryForm.LoginLogForm;
import com.database.backend.domain.queryForm.TracerForm;
import com.database.backend.enumeration.TracerEnum;
import com.database.backend.service.DataTracerService;
import com.database.backend.util.PageResult;
import com.database.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述
 * @日期 2023/11/6 10:34
 */
@RestController
public class DataTracerController {
    @Autowired
    DataTracerService dataTracerService;

    @PostMapping("/tracer")
    @Tracer(type = TracerEnum.REQUEST)
    public Result<PageResult<DataTracer>> getPostList(@RequestBody TracerForm pageParam) {
        return Result.success(dataTracerService.getTracerList(pageParam));
    }
}
