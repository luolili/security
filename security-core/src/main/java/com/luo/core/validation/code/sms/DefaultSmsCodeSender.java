package com.luo.core.validation.code.sms;

import com.luo.core.properties.SecurityProperties;

public class DefaultSmsCodeSender implements SmsCodeSender {
    private SecurityProperties securityProperties;

    @Override
    public void send(String mobile, String code) {
        System.out.println("send to" + mobile + ", code:" + code);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
