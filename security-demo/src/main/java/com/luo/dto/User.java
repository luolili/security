package com.luo.dto;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Date;

/**
 * 必须 加getter
 * https://blog.csdn.net/noob9527/article/details/86639911
 */
public class User {
    public interface UserSimpleView {

    }

    public interface UserDetailView extends UserSimpleView {

    }

    private Integer id;

    private String username;
    private String password;
    //如何接受Date 类型参数：
    private Date birthday;

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
