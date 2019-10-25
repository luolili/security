package com.luo.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        // 发出 get req
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username", "fg")
                .param("size", "2")
                .param("page", "3")//查第三页的数据
                .param("sort", "age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                // req 期望的 result,status code:200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //返回 的是集合，集合长度 是 3
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));

    }

    @Test
    public void whenQueryByIdSuccess() throws Exception {
        // 发出 get req
        String s = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                //.param("username","fg")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                // req 期望的 result,status code:200
                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("hu"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(s);


    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date date = new Date();
        long time = date.getTime();//传 timestamp
        String content = "{\"username\":\"hu\",\"birthday\":" + time + "}";
        // 发出 post req,必须加contentType ,否则415
        String s = mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                // req 期望的 result,status code:200
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        // {"id":1,"username":"hu","password":null,"birthday":1572004175181}
        System.out.println(s);

    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        long time = date.getTime();//传 timestamp
        String content = "{\"id\":\"1\",\"username\":\"hu\",\"birthday\":" + time + "}";
        // 发出 post req,必须加contentType ,否则415
        String s = mockMvc.perform(MockMvcRequestBuilders.put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                // req 期望的 result,status code:200
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        // {"id":1,"username":"hu","password":null,"birthday":1572004175181}
        System.out.println(s);

    }
}
