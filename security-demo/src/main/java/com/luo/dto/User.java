package com.luo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.luo.validator.MyConstraint;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * 必须 加getter
 * https://blog.csdn.net/noob9527/article/details/86639911
 *
 * @DecimaMax 最大数值
 * @Digits
 */
public class User {
    public interface UserSimpleView {

    }

    public interface UserDetailView extends UserSimpleView {

    }

    //@MyConstraint(message = "this is  a test")
    private Integer id;

    @NotBlank
    @ApiModelProperty(value = "姓名")
    private String username;
    private String password;
    //如何接受Date 类型参数：前台 传 时间戳
    @Past(message = "birthday必须是过去的时间")
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
