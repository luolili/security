package com.luo.core.validation.code;

import com.luo.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;
    @RequestMapping("/code/image")
    public void createCode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ImageCode imageCode = imageCodeGenerator.generate(req);
        sessionStrategy.setAttribute(new ServletWebRequest(req), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", resp.getOutputStream());

    }


}
