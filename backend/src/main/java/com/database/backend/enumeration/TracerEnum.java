package com.database.backend.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @描述
 * @日期 2023/11/6 10:44
 */
@AllArgsConstructor
@Getter
public enum TracerEnum {
    INSERT(1,"INSERT"),
    UPDATE(2,"UPDATE"),
    DELETE(3,"DELETE"),
    SELECT(4,"SELECT"),
    REQUEST(5,"REQUEST");

    private final int value;
    private final String desc;
}
