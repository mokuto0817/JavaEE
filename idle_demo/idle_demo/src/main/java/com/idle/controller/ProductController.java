package com.idle.controller;

import com.idle.entity.vo.ZydPage;
import com.idle.entity.vo.ZydResult;
import com.idle.enums.ReturnCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.idle.entity.Product;
import com.idle.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ZydResult list(@RequestParam(required = false, defaultValue = "1") int current,
                          @RequestParam(required = false, defaultValue = "10") int size,
                          Integer userId, String name, String highPrice, String minPrice, Integer number, String label, String status) {
        ZydPage<Product> page = productService.queryPage(current, size, userId, name, highPrice, minPrice, number, label, status);
        return ZydResult.SUCCESS(page);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public ZydResult getById(@PathVariable("id") Integer id) {
        return ZydResult.SUCCESS(productService.getById(id));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ZydResult save(@RequestBody Product product) {
        return ZydResult.SUCCESSORFAIL(productService.save(product), ReturnCodeEnum.SAVE_SUCCESS.getText(), ReturnCodeEnum.SAVE_FAILS.getText());
    }

    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public ZydResult updateById(@RequestBody Product product) {
        return ZydResult.SUCCESSORFAIL(productService.updateById(product), ReturnCodeEnum.UPDATE_SUCCESS.getText(), ReturnCodeEnum.UPDATE_FAILS.getText());
    }

    @RequestMapping(value = "/removeById/{id}", method = RequestMethod.DELETE)
    public ZydResult removeById(@PathVariable("id") Integer id) {
        return ZydResult.SUCCESSORFAIL(productService.removeById(id), ReturnCodeEnum.REMOVE_SUCCESS.getText(), ReturnCodeEnum.REMOVE_FAILS.getText());
    }
}
