package com.lxxx.controller.admin;

import com.lxxx.constant.JwtClaimsConstant;
import com.lxxx.dto.EmployeeAddDTO;
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
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<?> test() {
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
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
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
        //BeanUtils.copyProperties(employee,employeeLoginVO);

        return Result.success(employeeLoginVO);
    }

    /**
     * @Description: 员工分页查询
     * @param: [employeePageQueryDTO]
     * @return: com.lxxx.result.Result<com.lxxx.result.PageResult>
     * @Author: ILx
     * @Date: 2024/6/27
     */
    @GetMapping("/employee/page")
    @ApiOperation(value = "员工分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("分页查询，参数为：{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 员工删除
     *
     * @param: [id]
     * @return: com.lxxx.result.Result
     * @Author: ILx
     * @Date: 2024/7/15
     */
    @DeleteMapping("/employee/delete/{id}")
    @ApiOperation(value = "员工删除")
    public Result<?> delete(@PathVariable Long id) {
        log.info("员工删除操作,id{}", id);
        employeeService.delete(id);
        return Result.success();
    }

    /**
     * 员工启用禁用
     *
     * @param: [id, status]
     * @return: com.lxxx.result.Result<?>
     * @Author: ILx
     * @Date: 2024/7/24
     */
    @PutMapping("/employee/{id}/status")
    @ApiOperation(value = "员工启用禁用")
    public Result<?> updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        log.info("员工status修改id:{},status:{}", id, status);
        employeeService.updataStatus(id, status);
        return Result.success();
    }

    /**
     * 新增员工
     *
     * @param: [employeeAddDTO]
     * @return: com.lxxx.result.Result<?>
     * @Author: ILx
     * @Date: 2024/7/24
     */
    @PostMapping("/employee/add")
    @ApiOperation(value = "新增员工")
    public Result<?> addEmp(@RequestBody EmployeeAddDTO employeeAddDTO) {
        String name = employeeAddDTO.getName();
        String username = employeeAddDTO.getUsername();
        String password = employeeAddDTO.getPassword();
        employeeService.addEmp(name, username, password);
        return Result.success();
    }
}
