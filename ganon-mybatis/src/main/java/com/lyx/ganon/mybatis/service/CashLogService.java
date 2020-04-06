package com.lyx.ganon.mybatis.service;

import com.lyx.ganon.mybatis.mapper.BizCashLogMapper;
import com.lyx.ganon.mybatis.model.BizCashLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashLogService {

    @Autowired
    private BizCashLogMapper cashLogMapper;


    public int createCashLog(BizCashLog cashLog) {
        return cashLogMapper.insertSelective(cashLog);
    }
}
