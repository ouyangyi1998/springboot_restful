package com.jerry.springboot_restful.dao;

import com.jerry.springboot_restful.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    @Insert("insert into user(id,name,age) values (#{id},#{name},#{age})")
    void addUser(User user);
    @Update("update user set name=#{name},age=#{age} where id=#{id}")
    void updateUser(User user);
    @Delete("delete from user where id=#{id}")
    void deleteUser(int id);
    @Select("select id,name,age from user where name=#{name}")
    User findByName(String username);
    @Select("select id,name,age from user")
    List<User> findAll();
}
