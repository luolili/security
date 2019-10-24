package com.luo.dto;

/**
 * 必须 加getter
 * https://blog.csdn.net/noob9527/article/details/86639911
 */
public class User {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
