package com.luo.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        //所有的请求 都需要身份 认证
        http.formLogin()
                //跳转到自定义的 login 页面，返回登陆页面 or json
                .loginPage("/user-signIn.html")
                .loginProcessingUrl("/authentication/form")
                .and()
                .authorizeRequests()
                // 访问user-signIn.html 不需要认证
                .antMatchers("/user-signIn.html").permitAll()
                .anyRequest()
                .authenticated()
        .and()
                //Could not verify the provided CSRF token because your session was not found.
        .csrf().disable();//认证：表单验证


    }
    */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //所有的请求 都需要身份 认证
        http.formLogin()
                //跳转到自定义的 login 页面，返回登陆页面 or json
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .and()
                .authorizeRequests()
                // 访问user-signIn.html 不需要认证
                .antMatchers("/authentication/require").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //Could not verify the provided CSRF token because your session was not found.
                .csrf().disable();//认证：表单验证


    }


}
