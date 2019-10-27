package com.luo.core.validation.code;

import com.luo.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(HttpServletRequest req) {
        int defaultLen = securityProperties.getCode().getImage().getLength();
        int defaultExpireIn = securityProperties.getCode().getImage().getExpireIn();
        int defaultWidth = securityProperties.getCode().getImage().getWidth();
        int defaultHeight = securityProperties.getCode().getImage().getHeight();
        int width = ServletRequestUtils.getIntParameter(req, "width", defaultWidth);
        int height = ServletRequestUtils.getIntParameter(req, "height", defaultHeight);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(Color.GRAY);// 设置边框色
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Time New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(200, 250));
        for (int i = 0; i < 20; i++) {
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
            int d = random.nextInt(255);
            Color c = new Color(d, d, d);
            g.setColor(c);
            g.drawString(rand, 13 * i + 6, 16);
        }
        g.dispose();

        return new ImageCode(image, sRand, defaultExpireIn);
    }

    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}