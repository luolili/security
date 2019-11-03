package com.luo.security.app;

import com.luo.core.authentication.code.SmsCodeAuthenticationSecurityConfig;
import com.luo.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 资源 服务器
 * cent_id:项目启动的时候的clientId
 * 访问url:
 * http://localhost:8080/oauth/authorize?response_type=code&client_id=4a01db04-5861-4503-a335-9fb0f474068f
 * &redirect_uri=http://example.com&scope=all
 * <p>
 * http://localhost:8080/oauth/authorize?response_type=code&client_id=mee
 * &redirect_uri=http://example.com&scope=all
 * <p>
 * http://example.com/?code=de64eC
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    //和 component的 value 一样
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private SpringSocialConfigurer socialConfig;
    @Autowired
    SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                //跳转到自定义的 login 页面，返回登陆页面 or json
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)

                .and()
                .apply(socialConfig)
                .and()
                .authorizeRequests()
                // 访问user-signIn.html 不需要认证
                .antMatchers("/authentication/require",
                        securityProperties.getBrowserProperties().getLoginPage(),
                        "/code/*",
                        securityProperties.getBrowserProperties().getSignUpUrl(),
                        "/user/regist", "/session/invalid", "/user-logout.html").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //Could not verify the provided CSRF token because your session was not found.
                .csrf().disable()
                .apply(smsCodeAuthenticationSecurityConfig);//认证：表单验证

    }
}
