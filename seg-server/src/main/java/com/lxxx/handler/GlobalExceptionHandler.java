package com.lxxx.handler;

import com.lxxx.constant.MessageConstant;
import com.lxxx.exception.BaseException;
import com.lxxx.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


/**
 * 全局异常处理器
 *
 * @param:
 * @return:
 * @Author: ILx
 * @Date: 2024/7/26
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常-运行时异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public Result<?> exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 捕获数据库异常（SQLException）
     *
     * @param: [ex]
     * @return: com.lxxx.result.Result<?>
     * @Author: ILx
     * @Date: 2024/7/26
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<?> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")) {
            String[] split = message.split(" ");
            String username = split[2];
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        } else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

    /**
     * 捕获所有未处理的异常
     *
     * @param: [ex]
     * @return: com.lxxx.result.Result<?>
     * @Author: ILx
     * @Date: 2024/7/26
     */
    @ExceptionHandler
    public Result<?> exceptionHandler(Exception ex) {
        log.error("系统异常信息：{}", ex.getMessage());
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
}
