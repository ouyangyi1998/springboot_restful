package com.jerry.springboot_restful.web;

import com.jerry.springboot_restful.pojo.User;
import com.jerry.springboot_restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public boolean addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }
    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public boolean delete(@RequestParam(value = "userId",required = true)int userId)
    {
        return userService.deleteUser(userId);
    }
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public User findByUserName(@RequestParam(value = "username",required = true)String username)
    {
        return userService.findUserByName(username);
    }
    @RequestMapping(value = "/userAll",method = RequestMethod.GET)
    public List<User> findByUserAge()
    {
        return userService.findAll();
    }

}
