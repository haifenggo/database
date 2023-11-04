package com.database.backend.aop;

import com.alibaba.fastjson.JSONObject;
import com.database.backend.domain.entity.DataTracer;
import com.database.backend.service.LogService;
import com.database.backend.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect //切面类
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;

    @Around("@annotation(com.database.backend.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人ID - 当前登录ID
        //获取请求头中的jwt令牌, 解析令牌
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("userId");

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //调用原始目标方法运行
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        //方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        Long costTime = end - begin;

        DataTracer dataTracer = DataTracer.builder()
                .userId(operateUser)
                .content("操作人："+operateUser+"\n调用方法"+methodName+"\n调用参数"+methodParams)
                .createTime(LocalDateTime.now())
                .build();

        logService.addTracerLog(dataTracer);

        log.info("AOP记录操作日志: {}", dataTracer);

        return result;
    }
}
