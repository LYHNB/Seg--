package com.lxxx.service;

import com.lxxx.dto.EmployeeAddDTO;
import com.lxxx.dto.EmployeeLoginDTO;
import com.lxxx.dto.EmployeePageQueryDTO;
import com.lxxx.entity.Employee;
import com.lxxx.result.PageResult;

public interface EmployeeService {
    /**
     * 员工登录
     *
     * @param: [employeeLoginDTO]
     * @return: com.lxxx.entity.Employee
     * @Author: ILx
     * @Date: 2024/6/27
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 分页查询
     *
     * @param: [employeePageQueryDTO]
     * @return: com.lxxx.result.PageResult
     * @Author: ILx
     * @Date: 2024/6/27
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 员工删除操作
     *
     * @param: [id]
     * @return: void
     * @Author: ILx
     * @Date: 2024/7/15
     */
    void delete(Long id);

    /**
     * 员工启用禁用
     *
     * @param: [id, status]
     * @return: void
     * @Author: ILx
     * @Date: 2024/7/15
     */
    void updataStatus(Long id, Integer status);

    void addEmp(String name, String username, String password);
}
