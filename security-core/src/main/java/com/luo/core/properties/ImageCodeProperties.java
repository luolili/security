package com.luo.core.properties;

public class ImageCodeProperties extends SmsCodeProperties {
    private int width = 50;
    private int height = 20;

    public ImageCodeProperties() {
        setLength(4);
    }
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
