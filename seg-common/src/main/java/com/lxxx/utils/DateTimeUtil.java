package com.lxxx.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 *
 * @param:
 * @return:
 * @Author: ILx
 * @Date: 2024/7/24
 */
public class DateTimeUtil {
    // 定义日期时间格式

    /**
    * 获取当前时间-String类型
    * @param: []
    * @return: java.lang.String
    * @Author: ILx
    * @Date: 2024/7/24
    */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
