package com.database.backend.aop;

import com.database.backend.enumeration.TracerEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Tracer {
    TracerEnum type() default TracerEnum.REQUEST;


}
