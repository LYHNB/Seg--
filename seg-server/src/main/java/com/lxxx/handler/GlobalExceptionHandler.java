package com.lxxx.handler;

import com.lxxx.constant.MessageConstant;
import com.lxxx.exception.BaseException;
import com.lxxx.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
     * @param: [ex]
     * @return: com.lxxx.result.Result<?>
     * @Author: ILx
     * @Date: 2024/7/29
     */
    @ExceptionHandler(BaseException.class)
    public Result<?> exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 捕获数据库重复键异常（DuplicateKeyException）
     *
     * @param: [ex]
     * @return: com.lxxx.result.Result<?>
     * @Author: ILx
     * @Date: 2024/7/26
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result<?> exceptionHandler(DuplicateKeyException ex) {
        log.error("系统异常信息：{}，异常类型：{}", ex.getMessage(), ex.getClass().getName());
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")) {
            String duplicateValue = extractDuplicateEntry(message);
            String msg = "用户名[" + duplicateValue + "]" + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        } else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

    /**
     * 从异常信息中提取重复值
     *
     * @param: [message]
     * @return: java.lang.String
     * @Author: ILx
     * @Date: 2024/7/29
     */
    private String extractDuplicateEntry(String message) {
        //从 Duplicate entry '黄明' for key 'employee.idx_username' 中取出黄明
        Pattern pattern = Pattern.compile("Duplicate entry '(.*?)' for key");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return matcher.group(1);
        }
        // 返回一个默认值，防止未找到匹配时返回 null
        return "未知值";
    }

    /**
     * 捕获所有未处理的异常
     *
     * @param: [ex]
     * @return: com.lxxx.result.Result<?>
     * @Author: ILx
     * @Date: 2024/7/26
     */
/*    @ExceptionHandler(Exception.class)
    public Result<?> exceptionHandler(Exception ex) {
        log.error("异常类型：{}", ex.getClass().getName());
        log.error("系统异常信息：{}", ex.getMessage());
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }*/
}
