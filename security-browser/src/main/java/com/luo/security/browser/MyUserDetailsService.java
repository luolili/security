package com.luo.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

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
        logger.info("username:" + username);
        return new User(username, "123", true, true, true, false,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
