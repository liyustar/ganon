package com.lyx.ganon.mybatis.controller;

import com.lyx.ganon.mybatis.constant.CashType;
import com.lyx.ganon.mybatis.service.CashLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cash_logs")
public class CashLogController {

    @Autowired
    private CashLogService cashLogService;

    @GetMapping("/{id}")
    public ResponseObj<CashType> getCashTypeById(@PathVariable Integer id) {
        return ResponseObj.success(cashLogService.getCashTypeById(id));
    }

}
