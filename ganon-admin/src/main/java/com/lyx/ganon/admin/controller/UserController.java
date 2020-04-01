package com.lyx.ganon.admin.controller;

import com.lyx.ganon.admin.controller.request.CreateUserReq;
import com.lyx.ganon.admin.model.SysUser;
import com.lyx.ganon.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description UserController
 * @Author liyuxing
 * @Date 2019-12-08
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService service;

    /**
     * 创建用户
     * @param req
     * @return
     */
    @PostMapping
    public ResponseObj createUser(@RequestBody @Valid CreateUserReq req) {
        service.createUser(req);
        return ResponseObj.success();
    }

    /**
     * 查询用户
     * @param name
     * @return
     */
    @GetMapping("/{name}")
    public ResponseObj<SysUser> getUser(@PathVariable(value = "name") String name) {
        SysUser user = service.getUser(name);
        return ResponseObj.success(user);
    }

    @GetMapping("/{start}/to/{end}")
    public ResponseObj<List<SysUser>> getUserByRange(@PathVariable(value = "start") Integer start,
                                                     @PathVariable(value = "end") Integer end) {
        List<SysUser> users = service.getUserByRange(start, end);
        return ResponseObj.success(users);
    }

    @PostMapping("/byname")
    public ResponseObj<List<SysUser>> getUserByNames(@RequestBody List<String> names) {
        List<SysUser> users = service.getUserByNames(names);
        return ResponseObj.success(users);
    }

    @PostMapping("/byid")
    public ResponseObj<List<SysUser>> getUserByIds(@RequestBody List<Integer> ids) {
        List<SysUser> users = service.getUserByIds(ids);
        return ResponseObj.success(users);
    }

}
