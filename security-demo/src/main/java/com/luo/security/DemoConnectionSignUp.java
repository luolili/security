package com.luo.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 用 第三方登陆的时候，给用户 自动注册 本系统 的一个账号
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        //根据 connection 里面的信息 创建 本系统 用户，返回userId(唯一标识)

        return connection.getDisplayName();
    }
}
