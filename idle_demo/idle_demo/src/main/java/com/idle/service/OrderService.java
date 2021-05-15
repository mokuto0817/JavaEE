package com.idle.service;

import com.idle.entity.Order;
import com.idle.entity.vo.ZydPage;


public interface OrderService {
    ZydPage<Order> queryPage(int current, int size, String name, Integer userId);
    Boolean save(Order order);
    Boolean updateById(Order order);
    Boolean removeById(Integer id);
}

