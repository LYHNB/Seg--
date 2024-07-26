package com.lxxx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @ClassName: EmployeeAddDTO
 * @Author: ILx
 * @Date: 2024/7/24 22:19
 */

@Data
@ApiModel("新增员工是传递的数据模型")
public class EmployeeAddDTO {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
}
