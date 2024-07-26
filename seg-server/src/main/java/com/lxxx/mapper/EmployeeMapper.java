package com.lxxx.mapper;

import com.github.pagehelper.Page;
import com.lxxx.dto.EmployeePageQueryDTO;
import com.lxxx.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    /**
     * 根据用户名查询员工
     *
     * @param: [username]
     * @return: com.lxxx.entity.Employee
     * @Author: ILx
     * @Date: 2024/6/28
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 分页查询
     *
     * @param: [employeePageQueryDTO]
     * @return: com.github.pagehelper.Page<com.lxxx.entity.Employee>
     * @Author: ILx
     * @Date: 2024/6/28
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 删除员工
     *
     * @param: [id]
     * @return: void
     * @Author: ILx
     * @Date: 2024/7/15
     */
    void delete(Long id);

    /**
     * 更新员工
     *
     * @param: [employee]
     * @return: void
     * @Author: ILx
     * @Date: 2024/7/15
     */
    void update(Employee employee);

    /**
     * 新增员工
     *
     * @param: [employee]
     * @return: void
     * @Author: ILx
     * @Date: 2024/7/25
     */
    void add(Employee employee);
}
