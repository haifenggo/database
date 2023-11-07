package com.database.backend.service;

import com.database.backend.domain.entity.DataTracer;
import com.database.backend.domain.entity.LoginLog;
import com.database.backend.domain.queryForm.TracerForm;
import com.database.backend.util.PageResult;

/**
 * @描述
 * @日期 2023/11/6 10:35
 */
public interface DataTracerService {

    PageResult<DataTracer> getTracerList(TracerForm pageParam);

    void insertTracer(DataTracer dataTracer);
}
