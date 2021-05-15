package com.idle.service;

import com.idle.entity.Shopping;
import com.idle.entity.vo.ZydPage;

public interface ShoppingService {
    ZydPage<Shopping> queryPage(int current, int size, Integer type, Integer userId);
    Shopping getById(Integer id);
    Boolean save(Shopping shopping);
    Boolean updateById(Shopping shopping);
    Boolean removeById(Integer id);
}

