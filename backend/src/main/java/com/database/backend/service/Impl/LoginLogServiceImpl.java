package com.database.backend.service.Impl;

import com.database.backend.aop.Tracer;
import com.database.backend.domain.entity.Like;
import com.database.backend.domain.entity.LoginLog;
import com.database.backend.domain.queryForm.LoginLogForm;
import com.database.backend.enumeration.TracerEnum;
import com.database.backend.mapper.LoginLogMapper;
import com.database.backend.service.LoginLogService;
import com.database.backend.util.PageParam;
import com.database.backend.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @描述
 * @日期 2023/11/6 10:27
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    LoginLogMapper loginLogMapper;
    @Override
    public PageResult<LoginLog> getLoginLogList(LoginLogForm pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        Page<LoginLog> loginLogList = loginLogMapper.getLoginLogList(pageParam);
        PageResult<LoginLog> pageResult = new PageResult<>(loginLogList.getTotal(), loginLogList.getResult());
        return pageResult;
    }

    @Override
    public void insertLog(LoginLog loginLog) {
        loginLogMapper.insertLog(loginLog);
    }
}
