package com.lxxx.mapper;

import com.github.pagehelper.Page;
import com.lxxx.dto.EmployeePageQueryDTO;
import com.lxxx.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    /**
    * 根据姓名查询员工
    * @param: [username]
    * @return: com.lxxx.entity.Employee
    * @Author: ILx
    * @Date: 2024/6/28
    */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
    * 分页查询
    * @param: [employeePageQueryDTO]
    * @return: com.github.pagehelper.Page<com.lxxx.entity.Employee>
    * @Author: ILx
    * @Date: 2024/6/28
    */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
