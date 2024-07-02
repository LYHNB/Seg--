package com.lxxx.controller.admin;

import com.lxxx.constant.JwtClaimsConstant;
import com.lxxx.dto.EmployeeLoginDTO;
import com.lxxx.dto.EmployeePageQueryDTO;
import com.lxxx.entity.Employee;
import com.lxxx.properties.JwtProperties;
import com.lxxx.result.PageResult;
import com.lxxx.result.Result;
import com.lxxx.service.EmployeeService;
import com.lxxx.utils.JwtUtil;
import com.lxxx.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 分页查询返回结果
 * @ClassName: PageResult
 * @Author: ILx
 */
@RestController
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    @GetMapping("/test")
    @ApiOperation(value = "测试")
    public Result test(){
        return Result.success();
    }


    /** 
    * @Description: 员工登录
    * @Param: [employeeLoginDTO]
    * @return: com.lxxx.result.Result<com.lxxx.vo.EmployeeLoginVO>
    * @Author: ILx
    * @Date: 2024/6/27
    */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登陆成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ID, employee.getId());
        claims.put(JwtClaimsConstant.USERNAME, employee.getUsername());
        String token = JwtUtil.createJwt(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }
    /** 
    * @Description: 员工分页查询
    * @param: [employeePageQueryDTO]
    * @return: com.lxxx.result.Result<com.lxxx.result.PageResult>
    * @Author: ILx
    * @Date: 2024/6/27
    */
    @GetMapping("/page")
    @ApiOperation(value = "员工分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("分页查询，参数为：{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }
}
