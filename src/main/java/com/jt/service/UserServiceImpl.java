package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public String login(User user) {
        //Password encryption
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(md5Password);
        //Go to the database to find the username and password
        User userLogin = userMapper.selectOne(new QueryWrapper<>(user));//自动查找username和password相符的数据并返回
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.eq("username", user.getUsername())
//                        .eq("password", user.getPassword());
//        List<User> users = userMapper.selectList(userQueryWrapper);
        if (userLogin == null){
            return null;
        }
        // Generate key
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }

//    @Override
//    public PageResult findByPage(PageResult pageResult) {
//        String query = pageResult.getQuery();//query
//        Integer pageNum = pageResult.getPageNum();//page number
//        Integer pageSize = pageResult.getPageSize();//how many results on one page
//        Integer start = (pageNum-1)*pageSize;
//        List<User> userList = userMapper.findByPage(query,start,pageSize);
//        return pageResult.setRows(userList).setTotal(pageResult.getTotal());
//    }
    public PageResult findByPage(PageResult pageResult){
        // Get paginated results
        Page<User> page = new Page<>(pageResult.getPageNum(), pageResult.getPageSize());
        //get query condition
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("username", pageResult.getQuery());
        //paging + condition
        page= userMapper.selectPage(page, userQueryWrapper);

        return pageResult.setRows(page.getRecords())//Pass in three conditions pass out four
                         .setTotal(page.getTotal());
    }

    @Override
    public void changeUserStatus(User user) {
        userMapper.updateById(user);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setStatus(true);
        userMapper.insert(user);
    }

    @Override
    public User findUser(Integer id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }
}
