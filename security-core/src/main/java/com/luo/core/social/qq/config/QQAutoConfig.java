package com.luo.core.social.qq.config;


import com.luo.core.properties.QQProperties;
import com.luo.core.properties.SecurityProperties;
import com.luo.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        String providerId = qq.getProviderId();
        String appId = qq.getAppId();
        String appSecret = qq.getAppSecret();
        return new QQConnectionFactory(providerId, appId, appSecret);
    }
}
