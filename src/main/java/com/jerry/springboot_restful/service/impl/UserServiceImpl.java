package com.jerry.springboot_restful.service.impl;

import com.jerry.springboot_restful.dao.UserDao;
import com.jerry.springboot_restful.pojo.User;
import com.jerry.springboot_restful.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean addUser(User user) {
        boolean flag=false;
        try{
            userDao.addUser(user);
            flag=true;
        }catch (Exception e)
        {
            log.info("add failed");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUser(User user) {
        boolean flag=false;
        try {
            userDao.updateUser(user);
            flag=true;
        }catch (Exception e)
        {
            e.printStackTrace();
            log.info("update failed");
        }
        return flag;
    }

    @Override
    public boolean deleteUser(int id) {
       boolean flag=false;
       try{
           userDao.deleteUser(id);
           flag=true;
       }catch (Exception e)
       {
           e.printStackTrace();
           log.info("delete failed");
       }
       return flag;
    }

    @Override
    public User findUserByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
