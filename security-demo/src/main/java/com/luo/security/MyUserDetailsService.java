package com.luo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * @param username 前台 传过来的
     */
   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       logger.info("username:"+username);
        return new User(username,"123",
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("username:" + username);//表单登陆
        //每次 生成的密码 都不一样
        return new User(username, passwordEncoder.encode("123"), true, true, true, true,
                //ROLE_USER 认证 服务器的时候加，否则Access is denied，type=Forbidden, status=403
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));

    }


    // 社交网站登录
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("userId:" + userId);
        //每次 生成的密码 都不一样
        return new SocialUser(userId, passwordEncoder.encode("123"), true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }
}
