package com.luo.core.social.qq.config;


import com.luo.core.properties.QQProperties;
import com.luo.core.properties.SecurityProperties;
import com.luo.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
// 如果系统没有配置 qq的appId等信息，这个类就没有作用
@ConditionalOnProperty(prefix = "security.social.qq")//boot 自动配置注解
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
