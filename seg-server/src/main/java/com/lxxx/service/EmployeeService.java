package com.lxxx.service;

import com.lxxx.dto.EmployeeLoginDTO;
import com.lxxx.dto.EmployeePageQueryDTO;
import com.lxxx.entity.Employee;
import com.lxxx.result.PageResult;

public interface EmployeeService {
    /** 
    * 员工登录
    * @param: [employeeLoginDTO]
    * @return: com.lxxx.entity.Employee
    * @Author: ILx
    * @Date: 2024/6/27
    */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
    * 分页查询
    * @param: [employeePageQueryDTO]
    * @return: com.lxxx.result.PageResult
    * @Author: ILx
    * @Date: 2024/6/27
    */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
