package com.database.backend.controller;

import com.database.backend.aop.Tracer;
import com.database.backend.domain.entity.LoginLog;
import com.database.backend.domain.queryForm.LoginLogForm;
import com.database.backend.domain.queryForm.PostForm;
import com.database.backend.domain.vo.PostVO;
import com.database.backend.enumeration.TracerEnum;
import com.database.backend.service.LoginLogService;
import com.database.backend.util.PageParam;
import com.database.backend.util.PageResult;
import com.database.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述
 * @日期 2023/11/6 10:28
 */
@RestController
public class LoginLogController {
    @Autowired
    LoginLogService loginLogService;

    @PostMapping("/loginLog")
    @Tracer(type = TracerEnum.REQUEST)
    public Result<PageResult<LoginLog>> getPostList(@RequestBody LoginLogForm pageParam) {
        return Result.success(loginLogService.getLoginLogList(pageParam));
    }
}
