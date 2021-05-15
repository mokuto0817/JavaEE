package com.idle.controller;

import com.idle.entity.vo.ZydPage;
import com.idle.entity.vo.ZydResult;
import com.idle.enums.ReturnCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.idle.entity.User;
import com.idle.service.UserService;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ZydResult list(@RequestParam(required = false, defaultValue = "1") int current,
                          @RequestParam(required = false, defaultValue = "10") int size,
                          String username, String name, String tel) {
        ZydPage<User> page = userService.queryPage(current, size, username, name, tel);
        return ZydResult.SUCCESS(page);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public ZydResult getById(@PathVariable("id") Integer id) {
        return ZydResult.SUCCESS(userService.getById(id));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ZydResult save(@RequestBody User user) {
        ZydPage<User> userZydPage = userService.queryPage(1, 100, user.getUsername(), null, null);
        if(userZydPage.getTotal() > 0){
            return ZydResult.FAIL("用户名已存在");
        }
        return ZydResult.SUCCESSORFAIL(userService.save(user), ReturnCodeEnum.SAVE_SUCCESS.getText(), ReturnCodeEnum.SAVE_FAILS.getText());
    }

    @RequestMapping(value = "/updateById", method = RequestMethod.PUT)
    public ZydResult updateById(@RequestBody User user) {
        return ZydResult.SUCCESSORFAIL(userService.updateById(user), ReturnCodeEnum.UPDATE_SUCCESS.getText(), ReturnCodeEnum.UPDATE_FAILS.getText());
    }

    @RequestMapping(value = "/removeById/{id}", method = RequestMethod.DELETE)
    public ZydResult removeById(@PathVariable("id") Integer id) {
        return ZydResult.SUCCESSORFAIL(userService.removeById(id), ReturnCodeEnum.REMOVE_SUCCESS.getText(), ReturnCodeEnum.REMOVE_FAILS.getText());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ZydResult login(@RequestBody User user) {
        User u = userService.login(user);
        if(u == null){
            return ZydResult.FAIL("账号或密码错误");
        }
        return ZydResult.SUCCESS(u);
    }
}
