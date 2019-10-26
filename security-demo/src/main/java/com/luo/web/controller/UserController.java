package com.luo.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.luo.dto.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "创建用户")
    @PostMapping
    public User create(@RequestBody @Valid User user, BindingResult errors) {
        //err report
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> System.out.println(e.getDefaultMessage()));
        }
        user.setId(1);
        System.out.println(user.getUsername());
        System.out.println(user.getBirthday());//Fri Oct 25 19:49:35 CST 2019
        return user;
    }

    @PutMapping("{id}")
    public User update(@RequestBody @Valid User user, BindingResult errors) {
        //err report
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> System.out.println(e.getDefaultMessage()));
        }
        user.setId(1);
        System.out.println(user.getUsername());
        System.out.println(user.getBirthday());//Fri Oct 25 19:49:35 CST 2019
        return user;
    }

    //Pageable 是 spring data.domain
    //@RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping("")
    @JsonView(User.UserSimpleView.class)// 不展示密码
    public List<User> get(
            @RequestParam(value = "username", required = false, defaultValue = "d") String username,
            @PageableDefault(size = 2, page = 1, sort = "age,asc") Pageable pageable) {
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

    // \\d+ 只能接受数字
    //@RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    @JsonView(User.UserSimpleView.class)//展示密码
    public User getUSer(@ApiParam(value = "用户id") @PathVariable String id) {
        User user = new User();
        user.setUsername("hu");
        return user;
    }
}
