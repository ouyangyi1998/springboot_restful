package com.jerry.springboot_restful.service;


import com.jerry.springboot_restful.pojo.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    User findUserByName(String username);
    List<User> findAll();
}
