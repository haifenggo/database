package com.database.backend.service.Impl;

import com.database.backend.domain.entity.DataTracer;
import com.database.backend.domain.entity.LoginLog;
import com.database.backend.mapper.LogMapper;
import com.database.backend.service.LogService;
import com.database.backend.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    public void addLoginLog(LoginLog loginLog) {
        logMapper.addLoginLog(loginLog);
    }

    public PageResult getLoginLog(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        Page<LoginLog> pg=logMapper.getLoginLog();
        return new PageResult(pg.getTotal(),pg.getResult());
    }

    public PageResult TracerLog(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        Page<DataTracer> pg=logMapper.TracerLog();
        return new PageResult(pg.getTotal(),pg.getResult());
    }

    public void addTracerLog(DataTracer dataTracer) {
        logMapper.addTracerLog(dataTracer);
    }


}
