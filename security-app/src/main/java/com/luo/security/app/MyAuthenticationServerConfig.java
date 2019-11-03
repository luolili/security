package com.luo.security.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 认证 服务器
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
@EnableAuthorizationServer
public class MyAuthenticationServerConfig {

}
