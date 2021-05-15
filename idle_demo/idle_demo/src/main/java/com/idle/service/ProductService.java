package com.idle.service;

import com.idle.entity.Product;
import com.idle.entity.vo.ZydPage;

public interface ProductService {

    ZydPage<Product> queryPage(int current, int size, Integer userId, String name, String highPrice, String minPrice, Integer number, String label, String status);
    Product getById(Integer id);
    Boolean save(Product product);
    Boolean updateById(Product product);
    Boolean removeById(Integer id);
}

