package com.lxxx.log;

import java.lang.annotation.*;

/**
 * @Description:
 * @ClassName: Log
 * @Author: ILx
 * @Date: 2024/7/31 17:06
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    //执行操作
    String title() default "";
}
