package com.lxxx.interceptor;

import com.lxxx.constant.JwtClaimsConstant;
import com.lxxx.constant.MessageConstant;
import com.lxxx.exception.AccountNotFoundException;
import com.lxxx.exception.EmployeeNotLogin;
import com.lxxx.properties.JwtProperties;
import com.lxxx.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtTokenCheckInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验Jwt
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        log.info("登录校验");

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.pareseJwt(jwtProperties.getAdminSecretKey(), token);
            Long id = Long.valueOf(claims.get(JwtClaimsConstant.ID).toString());
            log.info("当前员工id:{}", id);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            throw new EmployeeNotLogin(MessageConstant.EMPLOYEE_NOT_LOGIN);
        }
    }
}
