package com.jt.service;

import com.jt.pojo.User;
import com.jt.vo.PageResult;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> findAll();

    String login(User user);

    PageResult findByPage(PageResult pageResult);

    void changeUserStatus(User user);

    void addUser(User user);

    User findUser(Integer id);

    void updateUser(User user);

    void deleteUser(Integer id);
}
