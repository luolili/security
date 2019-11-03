package com.luo.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luo.core.properties.LoginType;
import com.luo.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 登陆成功 之后的处理
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
        //implements AuthenticationSuccessHandler

{

    @Autowired
    private SecurityProperties securityProperties;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;

    //登陆 成功后 调用
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        logger.info("login success");
        if (LoginType.JSON.equals(securityProperties.getBrowserProperties().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            //默认的方式：继续道 call 接口
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
