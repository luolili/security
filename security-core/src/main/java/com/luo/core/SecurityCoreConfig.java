package com.luo.core;

import com.luo.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
//让 SecurityProperties 生效
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityCoreConfig {
}
