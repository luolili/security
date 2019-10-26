package com.luo.core.validation.code;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ValidateCodeController {

    private static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @RequestMapping("/code/image")
    public void createCode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ImageCode imageCode = createImageCode(req);
        sessionStrategy.setAttribute(new ServletWebRequest(req), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", resp.getOutputStream());

    }

    private ImageCode createImageCode(HttpServletRequest req) {

        return null;
    }
}
