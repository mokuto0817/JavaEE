package com.idle.controller;

import com.idle.entity.vo.ZydPage;
import com.idle.entity.vo.ZydResult;
import com.idle.enums.ReturnCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.idle.entity.Order;
import com.idle.service.OrderService;
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ZydResult list(@RequestParam(required = false, defaultValue = "1") int current,
                          @RequestParam(required = false, defaultValue = "10") int size,
                          String name, Integer userId) {
        ZydPage<Order> page = orderService.queryPage(current, size, name, userId);
        return ZydResult.SUCCESS(page);
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ZydResult save(@RequestBody Order order) {
        return ZydResult.SUCCESSORFAIL(orderService.save(order), ReturnCodeEnum.SAVE_SUCCESS.getText(), ReturnCodeEnum.SAVE_FAILS.getText());
    }

    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public ZydResult updateById(@RequestBody Order order) {
        return ZydResult.SUCCESSORFAIL(orderService.updateById(order), ReturnCodeEnum.UPDATE_SUCCESS.getText(), ReturnCodeEnum.UPDATE_FAILS.getText());
    }

    @RequestMapping(value = "/removeById/{id}", method = RequestMethod.DELETE)
    public ZydResult removeById(@PathVariable("id") Integer id) {
        return ZydResult.SUCCESSORFAIL(orderService.removeById(id), ReturnCodeEnum.REMOVE_SUCCESS.getText(), ReturnCodeEnum.REMOVE_FAILS.getText());
    }
}
