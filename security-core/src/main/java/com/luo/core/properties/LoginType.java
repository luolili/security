package com.luo.core.properties;

//登陆 成功 之后的处理：同步：继续进入接口；异步：返回json给front-end
public enum LoginType {
    REDIRECT,
    JSON;

}
