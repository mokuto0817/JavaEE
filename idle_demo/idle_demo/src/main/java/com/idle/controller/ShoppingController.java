package com.idle.controller;

import com.idle.entity.vo.ZydPage;
import com.idle.entity.vo.ZydResult;
import com.idle.enums.ReturnCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.idle.entity.Shopping;
import com.idle.service.ShoppingService;

@RestController
@RequestMapping("shopping")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ZydResult list(@RequestParam(required = false, defaultValue = "1") int current,
                          @RequestParam(required = false, defaultValue = "10") int size,
                          @RequestParam(required = false, defaultValue = "0") Integer type,
                          Integer userId) {
        ZydPage<Shopping> page = shoppingService.queryPage(current, size, type, userId);
        return ZydResult.SUCCESS(page);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public ZydResult getById(@PathVariable("id") Integer id) {
        return ZydResult.SUCCESS(shoppingService.getById(id));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ZydResult save(@RequestBody Shopping shopping) {
        return ZydResult.SUCCESSORFAIL(shoppingService.save(shopping), ReturnCodeEnum.SAVE_SUCCESS.getText(), ReturnCodeEnum.SAVE_FAILS.getText());
    }

    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public ZydResult updateById(@RequestBody Shopping shopping) {
        return ZydResult.SUCCESSORFAIL(shoppingService.updateById(shopping), ReturnCodeEnum.UPDATE_SUCCESS.getText(), ReturnCodeEnum.UPDATE_FAILS.getText());
    }

    @RequestMapping(value = "/removeById/{id}", method = RequestMethod.DELETE)
    public ZydResult removeById(@PathVariable("id") Integer id) {
        return ZydResult.SUCCESSORFAIL(shoppingService.removeById(id), ReturnCodeEnum.REMOVE_SUCCESS.getText(), ReturnCodeEnum.REMOVE_FAILS.getText());
    }
}
