package com.lxxx.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxxx.constant.MessageConstant;
import com.lxxx.constant.StatusConstant;
import com.lxxx.dto.EmployeeAddDTO;
import com.lxxx.dto.EmployeeLoginDTO;
import com.lxxx.dto.EmployeePageQueryDTO;
import com.lxxx.entity.Employee;
import com.lxxx.exception.PasswordErrorException;
import com.lxxx.exception.AccountNotFoundException;
import com.lxxx.exception.AccountLockedException;
import com.lxxx.mapper.EmployeeMapper;
import com.lxxx.result.PageResult;
import com.lxxx.service.EmployeeService;
import com.lxxx.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description: 分页查询返回结果
 * @ClassName: PageResult
 * @Author: ILx
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param: [employeeLoginDTO]
     * @return: com.lxxx.entity.Employee
     * @Author: ILx
     * @Date: 2024/7/3
     */
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {


        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();
        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus().equals(StatusConstant.DISABLE)) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 分页查询
     *
     * @param: [employeePageQueryDTO]
     * @return: com.lxxx.result.PageResult
     * @Author: ILx
     * @Date: 2024/6/27
     */
    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        //开始分页
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        //获得返回值并封装
        long total = page.getTotal();
        List<Employee> records = page.getResult();
        return new PageResult(total, records);
    }

    /**
     * 员工删除操作
     *
     * @param id
     * @param: [id]
     * @return: void
     * @Author: ILx
     * @Date: 2024/7/15
     */
    @Override
    public void delete(Long id) {
        employeeMapper.delete(id);
    }

    /**
     * 员工启用禁用
     *
     * @param: [id, status]
     * @return: void
     * @Author: ILx
     * @Date: 2024/7/15
     */
    @Override
    public void updataStatus(Long id, Integer status) {
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();
        employeeMapper.update(employee);
    }

    /**
     * 新增员工
     *
     * @param: [name, username, password]
     * @return: void
     * @Author: ILx
     * @Date: 2024/7/25
     */
    @Override
    public void addEmp(String name, String username, String password) {
        Employee employee = Employee.builder()
                .name(name)
                .username(username)
                .password(password)
                .status(StatusConstant.ENABLE)
                //.createTime(LocalDateTime.parse(DateTimeUtil.getCurrentDateTime()))
                .createTime(DateTimeUtil.getCurrentDateTime())
                .updateTime(DateTimeUtil.getCurrentDateTime())
                .build();
        employeeMapper.add(employee);
    }
}
