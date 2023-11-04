package com.database.backend.service;


import com.database.backend.domain.entity.DataTracer;
import com.database.backend.domain.entity.LoginLog;
import com.database.backend.util.PageResult;


public interface LogService {
    void addLoginLog(LoginLog loginLog);

    PageResult getLoginLog(Integer page, Integer pageSize);

    PageResult TracerLog(Integer page, Integer pageSize);

    void addTracerLog(DataTracer dataTracer);
}
