package com.idle.service;

import com.idle.entity.User;
import com.idle.entity.vo.ZydPage;

public interface UserService {
    ZydPage<User> queryPage(int current, int size, String username, String name, String tel);
    User getById(Integer id);
    Boolean save(User user);
    Boolean updateById(User user);
    Boolean removeById(Integer id);
    User login(User user);
}

