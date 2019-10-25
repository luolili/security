package com.luo.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("start filter");
        long start = new Date().getTime();
        chain.doFilter(request, response);
        System.out.println("time filter:" + (new Date().getTime() - start));
        System.out.println("end filter");
    }

    @Override
    public void destroy() {

    }
}
