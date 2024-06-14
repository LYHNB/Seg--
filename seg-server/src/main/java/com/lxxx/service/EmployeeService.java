package com.lxxx.service;

import com.lxxx.dto.EmployeeLoginDTO;
import com.lxxx.entity.Employee;

public interface EmployeeService {
    Employee login(EmployeeLoginDTO employeeLoginDTO);
}
