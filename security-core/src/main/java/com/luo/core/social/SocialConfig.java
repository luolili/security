package com.luo.core.social;

import com.luo.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * sql
     * create table UserConnection (userId varchar(255) not null,
     * providerId varchar(255) not null,
     * providerUserId varchar(255),
     * rank int not null,
     * displayName varchar(255),
     * profileUrl varchar(512),
     * imageUrl varchar(512),
     * accessToken varchar(512) not null,
     * secret varchar(512),
     * refreshToken varchar(512),
     * expireTime bigint,
     * primary key (userId, providerId, providerUserId));
     * create unique index UserConnectionRank on UserConnection(userId, providerId, rank);
     *
     * @param connectionFactoryLocator
     * @return
     */
    @Autowired(required = false)//不一定配置他
    private ConnectionSignUp connectionSignUp;
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator,
                Encryptors.noOpText());
        repository.setTablePrefix("tb_");
        if (connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    @Bean
    public SpringSocialConfigurer springSocialConfigurer() {
        String filterProcessUrl = securityProperties.getSocial().getFilterProcessUrl();
        MySpringSocialConfigurer configurer = new MySpringSocialConfigurer(filterProcessUrl);
        configurer.signupUrl(securityProperties.getBrowserProperties().getSignUpUrl());
        return configurer;
    }

    //获取 第三方登陆 人的信息
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator locator) {
        return new ProviderSignInUtils(locator, getUsersConnectionRepository(locator));
    }
}
