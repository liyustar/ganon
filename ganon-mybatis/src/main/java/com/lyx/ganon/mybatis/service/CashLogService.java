package com.lyx.ganon.mybatis.service;

import com.lyx.ganon.mybatis.mapper.BizCashLogMapper;
import com.lyx.ganon.mybatis.model.BizCashLog;
import com.lyx.ganon.mybatis.model.BizCashLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashLogService {

    @Autowired
    private BizCashLogMapper cashLogMapper;

    public List<BizCashLog> getCashLogs(Integer id) {
        BizCashLogExample example = new BizCashLogExample();
        example.createCriteria().andArticleIdEqualTo(id);
        return cashLogMapper.selectByExample(example);
    }

    public int createCashLog(BizCashLog cashLog) {
        return cashLogMapper.insertSelective(cashLog);
    }
}
