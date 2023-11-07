package com.database.backend.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.database.backend.aop.Tracer;
import com.database.backend.domain.entity.DataTracer;
import com.database.backend.domain.entity.LoginLog;
import com.database.backend.domain.queryForm.TracerForm;
import com.database.backend.enumeration.RedisEnum;
import com.database.backend.enumeration.TracerEnum;
import com.database.backend.mapper.DataTracerMapper;
import com.database.backend.service.DataTracerService;
import com.database.backend.service.RedisService;
import com.database.backend.util.PageParam;
import com.database.backend.util.PageResult;
import com.database.backend.util.TimeUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @描述
 * @日期 2023/11/6 10:35
 */
@Service
public class DataTracerServiceImpl implements DataTracerService {
    @Autowired
    DataTracerMapper dataTracerMapper;
    @Autowired
    RedisService redisService;


    @Override
    public PageResult<DataTracer> getTracerList(TracerForm pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        Page<DataTracer> tracerList = dataTracerMapper.getTracerList(pageParam);
        PageResult<DataTracer> pageResult = new PageResult<>(tracerList.getTotal(), tracerList.getResult());
        return pageResult;
    }

    @Override
    public void insertTracer(DataTracer dataTracer) {
        dataTracerMapper.insertTracer(dataTracer);
    }
}
