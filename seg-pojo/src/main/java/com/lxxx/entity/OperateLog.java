package com.lxxx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Description: 日志记录实体类
 * @ClassName: OperateLog
 * @Author: ILx
 * @Date: 2024/7/31 16:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {
    @ApiModelProperty("日志ID")
    private Long logId;

    @ApiModelProperty("操作人员")
    private String operateUser;

    @ApiModelProperty("执行操作")
    private String operateTitle;

    @ApiModelProperty("操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operateTime;

    @ApiModelProperty("请求方式")
    private String requestType;

    @ApiModelProperty("请求接口")
    private String requestUrl;

    @ApiModelProperty("请求类名")
    private String className;

    @ApiModelProperty("操作方法名")
    private String methodName;

    @ApiModelProperty("参数")
    private String methodParams;

    @ApiModelProperty("返回值")
    private String returnValue;

    @ApiModelProperty("执行耗时")
    private Long costTime;
}
