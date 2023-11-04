package com.database.backend.util;

import lombok.Data;

/**
 * @描述
 * @日期 2023/10/24 8:41
 */
@Data
public class Result<T> {
    Boolean ok;
    T data;
    String msg;
    private Result(Boolean _ok, T _data, String _msg){
        ok = _ok;
        data = _data;
        msg = _msg;
    }

    public static<T> Result<T> success(){
        return new Result<>(true, null, null);
    }

    public static<T> Result<T> success(T obj){
        return new Result<>(true, obj, null);
    }

    public static<T> Result<T> error(String msg){
        return new Result<>(false, null, msg);
    }


}
