package com.luo.core.properties;

public class BrowserProperties {
    private String loginPage = "/user-signIn.html";

    private LoginType loginType = LoginType.JSON;//默认 json

    private int rememberMeSeconds = 30;//30s

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {

        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
