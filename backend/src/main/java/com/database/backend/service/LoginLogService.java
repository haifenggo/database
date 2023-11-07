package com.database.backend.service;

import com.database.backend.domain.entity.LoginLog;
import com.database.backend.domain.queryForm.LoginLogForm;
import com.database.backend.util.PageParam;
import com.database.backend.util.PageResult;

/**
 * @描述
 * @日期 2023/11/6 10:26
 */
public interface LoginLogService {

    PageResult<LoginLog>  getLoginLogList(LoginLogForm pageParam);

    void insertLog(LoginLog loginLog);
}
