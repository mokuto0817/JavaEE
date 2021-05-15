package com.idle.service.impl;

import com.idle.entity.Product;
import com.idle.entity.vo.ZydPage;
import com.idle.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.idle.entity.Order;
import com.idle.mapper.OrderMapper;
import com.idle.service.OrderService;

import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductService productService;

    @Override
    public ZydPage<Order> queryPage(int current, int size, String name, Integer userId) {
        List<Order> list = orderMapper.selectPage((current - 1) * size, size, name, userId);
        Long count = orderMapper.selectPageCount(name, userId);
        return new ZydPage<>(current, count, list);
    }

    @Override
    public Boolean save(Order order) {
        order.setCreateTime(new Date());
        boolean b = orderMapper.insert(order) > 0;
        if(b){
            Product product = productService.getById(order.getProductId());
            product.setNumber(product.getNumber() - Integer.parseInt(order.getData1()));
            productService.updateById(product);
        }
        return b;
    }

    @Override
    public Boolean updateById(Order order) {
        return orderMapper.updateById(order) > 0;
    }

    @Override
    public Boolean removeById(Integer id) {
        return orderMapper.deleteById(id) > 0;
    }
}