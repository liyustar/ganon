package com.lyx.ganon.mybatis.controller;

import com.lyx.ganon.mybatis.model.BizUser;
import com.lyx.ganon.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseObj<Integer> addUser(@RequestBody BizUser user) {
        return ResponseObj.success(userService.addUser(user));
    }

    @DeleteMapping
    public ResponseObj addUser() {
        userService.deleteAll();
        return ResponseObj.success();
    }

}
