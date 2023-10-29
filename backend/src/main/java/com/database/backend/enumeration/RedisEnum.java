package com.database.backend.enumeration;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @描述
 * @日期 2023/10/24 9:09
 */
@AllArgsConstructor
public enum RedisEnum {
    USER("user","user");
    private final String prefix;
    private final String desc;

    public String getPrefix() {
        return prefix;
    }
}
