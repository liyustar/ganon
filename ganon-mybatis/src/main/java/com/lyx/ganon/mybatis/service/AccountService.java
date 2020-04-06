package com.lyx.ganon.mybatis.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lyx.ganon.mybatis.mapper.BizAccountMapper;
import com.lyx.ganon.mybatis.model.BizAccount;
import com.lyx.ganon.mybatis.model.BizAccountExample;
import com.lyx.ganon.mybatis.model.BizCashLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private BizAccountMapper accountMapper;

    @Autowired
    private CashLogService cashLogService;

    public int createAccount(BizAccount account) {
        accountMapper.insertSelective(account);
        return account.getId();
    }

    public void deleteAll() {
        accountMapper.deleteByExample(null);
    }

    @Transactional
    public int transUserMoney(int fromUserId, int toUserId, double amt, String remark) {
        BizAccountExample example = new BizAccountExample();
        example.createCriteria().andUserIdIn(Lists.newArrayList(fromUserId, toUserId));
        List<BizAccount> bizAccounts = accountMapper.selectByExampleForUpdate(example);

        Optional<BizAccount> fromAcc = bizAccounts.stream().filter(account -> account.getUserId().equals(fromUserId)).findFirst();
        Optional<BizAccount> toAcc = bizAccounts.stream().filter(account -> account.getUserId().equals(toUserId)).findFirst();

        Preconditions.checkState(fromAcc.isPresent());
        Preconditions.checkState(toAcc.isPresent());

        if (fromAcc.get().getAmt() < amt) {
            throw new RuntimeException("账户<" + fromAcc.get().getId() + ">不够钱转账，" + fromAcc.get().getAmt());
        }
        fromAcc.get().setAmt(fromAcc.get().getAmt() - amt);
        toAcc.get().setAmt(toAcc.get().getAmt() + amt);

        accountMapper.updateByPrimaryKeySelective(fromAcc.get());
        accountMapper.updateByPrimaryKeySelective(toAcc.get());

        // 记录流水
        BizCashLog cashLog = new BizCashLog();
        cashLog.setAccFrom(fromAcc.get().getAccCode());
        cashLog.setAccTo(toAcc.get().getAccCode());
        cashLog.setAmt(amt);
        cashLog.setArticleId(-1);
        cashLog.setRemark(remark);
        return cashLogService.createCashLog(cashLog);
    }
}
