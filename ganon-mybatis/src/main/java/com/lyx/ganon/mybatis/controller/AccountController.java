package com.lyx.ganon.mybatis.controller;

import com.lyx.ganon.mybatis.model.BizAccount;
import com.lyx.ganon.mybatis.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseObj<Integer> createAccount(@RequestBody BizAccount account) {
        return ResponseObj.success(accountService.createAccount(account));
    }

    @DeleteMapping
    public ResponseObj deleteAll() {
        accountService.deleteAll();
        return ResponseObj.success();
    }

}
