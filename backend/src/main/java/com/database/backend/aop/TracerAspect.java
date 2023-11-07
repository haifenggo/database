package com.database.backend.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.database.backend.context.BaseContext;
import com.database.backend.domain.entity.DataTracer;
import com.database.backend.domain.entity.User;
import com.database.backend.enumeration.TracerEnum;
import com.database.backend.service.DataTracerService;
import com.database.backend.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @描述
 * @日期 2023/11/6 10:49
 */
@Aspect
@Component
public class TracerAspect {
    @Autowired
    DataTracerService tracerService;

    @Autowired
    UserService userService;

    @Pointcut("@annotation(com.database.backend.aop.Tracer)")
    public void tracerCut() {
    }

    @Around("tracerCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        Long costTime = end - begin;
        DataTracer dataTracer = generateTracer(joinPoint, costTime, result);
        tracerService.insertTracer(dataTracer);
        return result;
    }

    private DataTracer generateTracer(ProceedingJoinPoint joinPoint, Long costTime, Object result) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = method.getName();
        Tracer annotation = method.getAnnotation(Tracer.class);
        Object[] args = joinPoint.getArgs();
        TracerEnum tracerEnum = annotation.type();
//        String returnValue = JSONObject.toJSONString(result);
        Integer userId = BaseContext.getCurrentId();
        User user = userService.selectById(userId);
        String content = "操作用户:" + (user == null ? "游客" : user.getUsername())
//                + "\n" + "操作类型:" + tracerEnum.getDesc()
                + "\n" + "执行耗时:" + costTime + "ms"
                + "\n" + "调用方法:" + methodName
                + "\n" + "方法参数:" + JSON.toJSONString(args);
//                + "\n" + "执行结果:" + returnValue;

        DataTracer dataTracer = DataTracer.builder()
                .userId(userId == null ? 0 : userId)
                .content(content)
                .type(tracerEnum.getValue())
                .createTime(LocalDateTime.now())
                .build();
        return dataTracer;
    }
}
