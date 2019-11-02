package com.luo.security.browser;
import com.luo.core.properties.SecurityProperties;
import com.luo.security.browser.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse auth(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        SavedRequest savedRequest = requestCache.getRequest(req, resp);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                // 用户配置自己的登陆页面
                String userLoginPage = securityProperties.getBrowserProperties().getLoginPage();
                redirectStrategy.sendRedirect(req, resp, userLoginPage);

            }
        }

        return new SimpleResponse("需要身份认证");
    }


    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserIfo(HttpServletRequest req) {
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection =
                providerSignInUtils.getConnectionFromSession(new ServletWebRequest(req));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return userInfo;
    }

    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse sessionInvalid(HttpServletRequest req) {

        String msg = "session 失效";
        return new SimpleResponse(msg);
    }
}
