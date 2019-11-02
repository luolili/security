package com.luo.security.browser;

import com.luo.core.authentication.code.SmsCodeAuthenticationSecurityConfig;
import com.luo.core.authentication.code.SmsCodeFilter;
import com.luo.core.properties.SecurityProperties;
import com.luo.core.validation.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    //和 component的 value 一样
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    //引入 其他模块的配置
    @Autowired
    private SpringSocialConfigurer socialConfig;
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

//    @Autowired
//    DataSource dataSource;

//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);//创建了之后不能再建
//        return tokenRepository;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();
        int rememberMeSeconds = securityProperties.getBrowserProperties().getRememberMeSeconds();
        http
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                //跳转到自定义的 login 页面，返回登陆页面 or json
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)

//                .and()
//                .rememberMe()
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(rememberMeSeconds)
//                .userDetailsService(userDetailsService)

                .and()
                .apply(socialConfig)

                .and()
                .authorizeRequests()
                // 访问user-signIn.html 不需要认证
                .antMatchers("/authentication/require",
                        securityProperties.getBrowserProperties().getLoginPage(),
                        "/code/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //Could not verify the provided CSRF token because your session was not found.
                .csrf().disable()
                .apply(smsCodeAuthenticationSecurityConfig);//认证：表单验证

    }


}
