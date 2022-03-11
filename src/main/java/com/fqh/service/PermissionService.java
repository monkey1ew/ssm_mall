package com.fqh.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * @author 海盗狗
 * @version 1.0
 * 登录权限验证
 *      admin:管理员
 *          vip5-vip4-vip3-vip2-vip1-普通用户
 * 账号状态验证
 */
@Service
@Aspect
public class PermissionService {

    @Pointcut(value = ("execution(* com.fqh.controller.UserController.login(..))"))
    public void pointcut(){};

    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            String username = (String) args[i];
        }
    }
}
