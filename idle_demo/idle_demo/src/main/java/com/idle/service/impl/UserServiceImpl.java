package com.idle.service.impl;


import com.idle.entity.User;
import com.idle.entity.vo.ZydPage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.idle.mapper.UserMapper;
import com.idle.service.UserService;

import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;

    @Override
    public ZydPage<User> queryPage(int current, int size, String username, String name, String tel) {
        List<User> list = userMapper.selectPage((current - 1) * size, size, username, name, tel);
        Long count = userMapper.selectPageCount(username, name, tel);
        return new ZydPage<>(current, count, list);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public Boolean save(User user) {
        user.setCreateTime(new Date());
        return userMapper.insert(user) > 0;
    }

    @Override
    public Boolean updateById(User User) {
        return userMapper.updateById(User) > 0;
    }

    @Override
    public Boolean removeById(Integer id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public User login(User user) {
        return userMapper.selectByUserNameAndPassword(user);
    }
}