package com.luo.core.validation.code;

import com.luo.core.properties.SecurityProperties;
import com.luo.core.validation.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;
    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @RequestMapping("/code/image")
    public void createCode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(req);
        sessionStrategy.setAttribute(new ServletWebRequest(req), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", resp.getOutputStream());

    }

    @RequestMapping("/code/sms")
    public void createSmsCode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ValidateCode smsCode = smsCodeGenerator.generate(req);
        sessionStrategy.setAttribute(new ServletWebRequest(req), SESSION_KEY + "sms", smsCode);
        String mobile = ServletRequestUtils.getStringParameter(req, "mobile");
        smsCodeSender.send(mobile, smsCode.getCode());
    }

}
