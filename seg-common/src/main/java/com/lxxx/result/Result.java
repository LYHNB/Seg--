package com.lxxx.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 分页查询返回结果
 * @ClassName: PageResult
 * @Author: ILx
 */
@Data
public class Result<T> implements Serializable {
    /**
     * 编码：1成功，0和其它数字为失败
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}
