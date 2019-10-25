package com.luo.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TimeHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("pre");
        HandlerMethod method = (HandlerMethod) handler;
        String handlerClassName = method.getBean().getClass().getName();
        String methodName = method.getMethod().getName();
        System.out.println(handlerClassName);
        System.out.println(methodName);
        request.setAttribute("startTime", new Date().getTime());
        return true;//call controller method //无法获得 方法参数 的 值
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterCompletion");
        long start = (long) request.getAttribute("startTime");
        System.out.println("time:" + (new Date().getTime() - start));
    }
}
