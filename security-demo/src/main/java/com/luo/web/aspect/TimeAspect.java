package com.luo.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.luo.web.controller.UserController.*(..))")
    public Object handlControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg:" + arg);
        }
        long start = new Date().getTime();
        Object o = pjp.proceed();//call controller method
        System.out.println("time:" + (new Date().getTime() - start));
        System.out.println("time aspect end");
        return o;
    }
}
