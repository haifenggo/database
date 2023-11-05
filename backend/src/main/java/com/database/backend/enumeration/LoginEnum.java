package com.database.backend.enumeration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @描述
 * @日期 2023/11/5 14:11
 */
@AllArgsConstructor
@Getter
public enum LoginEnum {

    SUCCESS(0, "登录成功"),

    FAILURE(1, "登录失败");
    private int value;
    private String desc;


}
