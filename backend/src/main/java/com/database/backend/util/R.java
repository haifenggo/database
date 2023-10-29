package com.database.backend.util;

import lombok.Data;

/**
 * @描述
 * @日期 2023/10/24 8:41
 */
@Data
public class R<T> {
    Boolean ok;
    T data;
    String msg;
    private R(Boolean _ok, T _data, String _msg){
        ok = _ok;
        data = _data;
        msg = _msg;
    }

    public static<T> R<T> success(){
        return new R<>(true, null, null);
    }

    public static<T> R<T> success(T obj){
        return new R<>(true, obj, null);
    }

    public static<T> R<T> error(String msg){
        return new R<>(false, null, msg);
    }
}
