package com.luo.core.validation.code.sms;

public interface SmsCodeSender {

    void send(String mobile, String code);

}
