package com.luo.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;


public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    private String appId;
    private String openId;

    private static final String URL_GET_OPENID =
            "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final String URL_GET_USERINFO =
            "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private ObjectMapper objectMapper;

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);//send req
        System.out.println(result);
        this.openId = StringUtils.substringBetween(result, "\"openid\"", "}");

    }

    @Override
    public QQUserInfo getQQUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        try {
            QQUserInfo userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (IOException e) {
            throw new RuntimeException("-err get user info-");
        }
    }


}
