package com.lxxx.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxxx.dto.EmployeeLoginDTO;
import com.lxxx.entity.OperateLog;
import com.lxxx.mapper.LogMapper;
import com.lxxx.properties.JwtProperties;
import com.lxxx.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @Description:
 * @ClassName: LogAspect
 * @Author: ILx
 * @Date: 2024/7/31 17:13
 */
@Component
@Aspect
public class LogAspect {
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private LogMapper logMapper;

    @Around("@annotation(com.lxxx.log.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //获取Log注解信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);


        //获取执行操作的用户名
        /*String jwt = request.getHeader("token");
        Claims claims = JwtUtil.pareseJwt(jwtProperties.getAdminSecretKey(), jwt);
        String operateUser = (String) claims.get("username");*/

        //执行操作
        String operateTitle = logAnnotation.title();

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //传递参数
        Object[] args = joinPoint.getArgs();
        String methodParams = JSON.toJSONString(args[0]);

        //请求方式
        String requestType = request.getMethod();

        //请求地址
        String requestUrl = request.getServletPath();

        long begin = System.currentTimeMillis();
        //调用原始方法执行
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        //返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        Long costTime = end - begin;

        // 区分登录操作与其他操作
        String operateUser;
        if (isLoginRequest(requestUrl, methodName)) {
            // 登录操作时，用户信息从请求参数中获取
            operateUser = extractUsernameFromArgs(args);
        } else {
            // 非登录操作时，用户信息从JWT token中获取
            String jwt = request.getHeader("token");
            Claims claims = JwtUtil.pareseJwt(jwtProperties.getAdminSecretKey(), jwt);
            operateUser = (String) claims.get("username");
        }

        //记录日志操作
        OperateLog operateLog = new OperateLog(null, operateUser, operateTitle, operateTime, requestType, requestUrl, className, methodName, methodParams, returnValue, costTime);
        logMapper.insert(operateLog);
        return result;
    }

    private boolean isLoginRequest(String requestUrl, String methodName) {
        // 判断是否为登录请求，可以根据请求路径或方法名来判断
        return requestUrl.contains("/login") || "login".equalsIgnoreCase(methodName);
    }

    private String extractUsernameFromArgs(Object[] args) {
        // 从请求参数中提取用户名，假设参数是EmployeeLoginDTO对象
        if (args != null && args.length > 0) {
            // 假设第一个参数是EmployeeLoginDTO对象
            Object arg = args[0];
            if (arg instanceof EmployeeLoginDTO) {
                EmployeeLoginDTO loginDTO = (EmployeeLoginDTO) arg;
                // 获取用户名
                return loginDTO.getUsername();
            }
        }
        return "unknown";
    }
}
