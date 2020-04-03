package com.lyx.ganon.admin.biz.controller;

import com.lyx.ganon.admin.biz.controller.request.AddAccountVO;
import com.lyx.ganon.admin.biz.model.BizAccount;
import com.lyx.ganon.admin.biz.service.AccountService;
import com.lyx.ganon.admin.controller.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biz/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    public ResponseObj addAccount(@RequestBody AddAccountVO param) {
        int rtn = service.add(param);
        return ResponseObj.success(rtn);
    }

    @PutMapping("/{accCode}/incr_amt")
    public ResponseObj incrAmt(@PathVariable String accCode, @RequestParam Double incrAmt) {
        int rtn = service.incrAmt(accCode, incrAmt);
        return ResponseObj.success(rtn);
    }

    @GetMapping("/{start}/to/{end}")
    public ResponseObj<List<BizAccount>> getByIdRange(@PathVariable(value = "start") Integer start,
                                                        @PathVariable(value = "end") Integer end) {
        List<BizAccount> accounts = service.getByIdRange(start, end);
        return ResponseObj.success(accounts);
    }

    @PostMapping("/byacccode")
    public ResponseObj<List<BizAccount>> getByAccCodes(@RequestBody List<String> accCodes) {
        List<BizAccount> accounts = service.getByAccCodes(accCodes);
        return ResponseObj.success(accounts);
    }

    @PostMapping("/byid")
    public ResponseObj<List<BizAccount>> getByIds(@RequestBody List<Integer> ids) {
        List<BizAccount> accounts = service.getByIds(ids);
        return ResponseObj.success(accounts);
    }

}
