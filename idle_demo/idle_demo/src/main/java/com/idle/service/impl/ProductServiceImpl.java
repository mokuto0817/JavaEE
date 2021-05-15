package com.idle.service.impl;

import com.idle.entity.Product;
import com.idle.entity.vo.ZydPage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.idle.mapper.ProductMapper;
import com.idle.service.ProductService;

import java.util.Date;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    @SuppressWarnings("all")
    private ProductMapper productMapper;


    @Override
    public ZydPage<Product> queryPage(int current, int size, Integer userId, String name, String highPrice, String minPrice, Integer number, String label, String status) {
        List<Product> list = productMapper.selectPage((current - 1) * size, size, userId, name, highPrice, minPrice, number, label, status);
        Long count = productMapper.selectPageCount(userId, name, highPrice, minPrice, number, label, status);
        return new ZydPage<>(current, count, list);
    }


    @Override
    public Product getById(Integer id) {
        return productMapper.selectById(id);
    }


    @Override
    public Boolean save(Product product) {
        product.setCreateTime(new Date());
        return productMapper.insert(product) > 0;
    }


    @Override
    public Boolean updateById(Product Product) {
        return productMapper.updateById(Product) > 0;
    }

    @Override
    public Boolean removeById(Integer id) {
        return productMapper.deleteById(id) > 0;
    }
}