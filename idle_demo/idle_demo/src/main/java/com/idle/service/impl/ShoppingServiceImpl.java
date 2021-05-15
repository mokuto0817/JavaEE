package com.idle.service.impl;

import com.idle.entity.Shopping;
import com.idle.entity.vo.ZydPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.idle.mapper.ShoppingMapper;
import com.idle.service.ShoppingService;

import java.util.Date;
import java.util.List;

@Service("shoppingService")
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    @SuppressWarnings("all")
    private ShoppingMapper shoppingMapper;


    @Override
    public ZydPage<Shopping> queryPage(int current, int size, Integer type, Integer userId) {
        List<Shopping> list = shoppingMapper.selectPage((current - 1) * size, size, type, userId);
        Long count = shoppingMapper.selectPageCount(type, userId);
        return new ZydPage<>(current, count, list);
    }


    @Override
    public Shopping getById(Integer id) {
        return shoppingMapper.selectById(id);
    }

    @Override
    public Boolean save(Shopping shopping) {
        List<Shopping> shoppings = shoppingMapper.selectPage(0, 10000, shopping.getType(), shopping.getUserId());
        for(Shopping shopping1: shoppings){
            if(shopping1.getProductId() == shopping.getProductId()){
                BeanUtils.copyProperties(shopping1, shopping);
                shopping.setNumber(shopping.getNumber() + 1);
                break;
            }
        }
        if(shopping.getId() != null){
            return shoppingMapper.updateById(shopping) > 0;
        }
        shopping.setCreateTime(new Date());
        return shoppingMapper.insert(shopping) > 0;

    }

    @Override
    public Boolean updateById(Shopping shopping) {
        return shoppingMapper.updateById(shopping) > 0;
    }

    @Override
    public Boolean removeById(Integer id) {
        return shoppingMapper.deleteById(id) > 0;
    }
}