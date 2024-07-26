package com.lxxx.exception;

/**
 * 业务异常
 */
public class BaseException extends RuntimeException {

    public BaseException() {
        super("服务器异常，请联系管理员");
    }

    public BaseException(String msg) {
        super(msg);
    }



}
