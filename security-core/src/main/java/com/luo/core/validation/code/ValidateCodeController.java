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
    @RequestMapping("/code/image")
    public void createCode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ImageCode imageCode = generate(req);
        sessionStrategy.setAttribute(new ServletWebRequest(req), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", resp.getOutputStream());

    }

    private ImageCode generate(HttpServletRequest req) {
        int defaultLen = securityProperties.getCode().getImage().getLength();
        int defaultExpireIn = securityProperties.getCode().getImage().getExpireIn();
        int defaultWidth = securityProperties.getCode().getImage().getWidth();
        int defaultHeight = securityProperties.getCode().getImage().getWidth();
        int width = ServletRequestUtils.getIntParameter(req, "width", defaultWidth);
        int height = ServletRequestUtils.getIntParameter(req, "height", defaultHeight);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();
        Random random = new Random();
        Color color = new Color(2);
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.setColor(color);
        for (int i = 0; i < 155; i++) {

            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, xl, yl);

        }
        String sRand = "";

        for (int i = 0; i < defaultLen; i++) {

            String rand = String.valueOf(random);
            sRand += rand;
            Color c = new Color(20 + random.nextInt(110));

            g.setColor(c);

            g.drawString(rand, 13 * i + 6, 16);
        }
        g.dispose();

        return new ImageCode(image, sRand, defaultExpireIn);
    }
}
