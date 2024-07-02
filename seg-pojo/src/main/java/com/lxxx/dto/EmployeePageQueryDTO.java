package com.lxxx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @Description: 员工分页查询时传递的数据类
 * @ClassName: EmployeePageQueryDTO
 * @Author: ILx
 * @Date: 2024/6/27 21:26
 */
@Data
@ApiModel("员工分页查询时传递的数据模型")
public class EmployeePageQueryDTO {
    @ApiModelProperty("员工姓名")
    private String name;

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("每页显示记录数")
    private Integer pageSize;
}
