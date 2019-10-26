package com.luo.security.browser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //所有的请求 都需要身份 认证
        http.formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();//认证：表单验证
    }
}
