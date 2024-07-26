package com.lxxx.exception;

/**
 * @Description:
 * @ClassName: EmployeeNotLogin
 * @Author: ILx
 * @Date: 2024/7/4 15:36
 */
public class EmployeeNotLogin extends BaseException{
    public EmployeeNotLogin() {
    }

    public EmployeeNotLogin(String msg) {
        super(msg);
    }
}
