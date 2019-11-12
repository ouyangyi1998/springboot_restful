package com.jerry.springboot_restful.web;

import com.jerry.springboot_restful.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloWorldController {
    @RequestMapping("/hello")
    public String index()
    {
        return "hello world";
    }
    @RequestMapping("/getUser")
    public User getUser()
    {
        User user=new User();
        user.setId(2);
        user.setName("jerry");
        return user;
    }
}
