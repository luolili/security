package com.luo.core.validation.code;

import com.luo.core.properties.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(HttpServletRequest req) {
        int defaultExpireIn = securityProperties.getCode().getSms().getExpireIn();
        int length = securityProperties.getCode().getSms().getLength();
        String code = RandomStringUtils.randomNumeric(length);
        return new ValidateCode(code, defaultExpireIn);
    }


    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
