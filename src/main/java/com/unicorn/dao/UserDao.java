package com.unicorn.dao;

import com.unicorn.model.User;

import java.util.List;

//Mapper动态代理
public interface UserDao {
    User findUserById(int id) throws Exception;
    List<User> findUserByName(String username) throws Exception;
    void insertUser(User user) throws Exception;
    void deleteUserById(int id) throws Exception;
    void updateUser(User user) throws Exception;
}
