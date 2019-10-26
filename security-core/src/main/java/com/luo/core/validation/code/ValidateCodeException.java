package com.luo.core.validation.code;

import org.springframework.security.core.AuthenticationException;

import javax.security.auth.message.AuthException;

public class ValidateCodeException extends AuthenticationException {


    public ValidateCodeException(String msg) {
        super(msg);
    }
}
