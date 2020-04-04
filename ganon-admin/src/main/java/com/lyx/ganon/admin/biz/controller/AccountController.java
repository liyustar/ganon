package com.lyx.ganon.admin.biz.controller;

import com.lyx.ganon.admin.biz.controller.request.AddAccountVO;
import com.lyx.ganon.admin.biz.model.BizAccount;
import com.lyx.ganon.admin.biz.service.AccountService;
import com.lyx.ganon.admin.controller.ResponseObj;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biz/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    @Timed(percentiles = {0.9, 0.95, 0.99})
    public ResponseObj addAccount(@RequestBody AddAccountVO param) {
        int rtn = service.add(param);
        return ResponseObj.success(rtn);
    }

    @PutMapping("/{accCode}/incr_amt")
    @Timed(percentiles = {0.9, 0.95, 0.99})
    public ResponseObj incrAmt(@PathVariable String accCode, @RequestParam Double incrAmt) {
        int rtn = service.incrAmt(accCode, incrAmt);
        return ResponseObj.success(rtn);
    }

    @PutMapping("/{accCode}/incr_amt_v2")
    @Timed(percentiles = {0.9, 0.95, 0.99})
    public ResponseObj incrAmtV2(@PathVariable String accCode, @RequestParam Double incrAmt) {
        int rtn = service.incrAmtV2(accCode, incrAmt);
        if (rtn == 1) {
            return ResponseObj.success(rtn);
        } else {
            throw new RuntimeException("update fail");
        }
    }

    @GetMapping("/{start}/to/{end}")
    @Timed(percentiles = {0.9, 0.95, 0.99})
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
