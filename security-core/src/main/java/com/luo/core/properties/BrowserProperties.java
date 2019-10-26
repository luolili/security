package com.luo.core.properties;

public class BrowserProperties {
    private String loginPage = "/user-signIn.html";

    private LoginType loginType = LoginType.JSON;//默认 json
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
