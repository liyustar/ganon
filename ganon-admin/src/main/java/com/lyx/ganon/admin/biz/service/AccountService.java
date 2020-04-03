package com.lyx.ganon.admin.biz.service;

import com.lyx.ganon.admin.biz.controller.request.AddAccountVO;
import com.lyx.ganon.admin.biz.mapper.BizAccountMapper;
import com.lyx.ganon.admin.biz.model.BizAccount;
import com.lyx.ganon.admin.biz.model.BizAccountExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private BizAccountMapper accountMapper;

    @Transactional
    public int addOrUpdate(AddAccountVO addAccountVO) {
        Optional<BizAccount> bizAccount = Optional.ofNullable(accountMapper.selectOneForUpdate(addAccountVO.getAccCode()));

        if (bizAccount.isPresent()) {
            bizAccount.get().setAmt(bizAccount.get().getAmt() + addAccountVO.getAmt());
            return accountMapper.updateByPrimaryKey(bizAccount.get());
        } else {
            BizAccount newAccount = new BizAccount();
            newAccount.setAccCode(addAccountVO.getAccCode());
            newAccount.setAmt(addAccountVO.getAmt());

            BizAccountExample example = new BizAccountExample();
            example.createCriteria().andAccCodeEqualTo(addAccountVO.getAccCode());
            long l = accountMapper.countByExample(example);
            if (l > 0) {
                throw new RuntimeException("已有账户，" + addAccountVO.getAccCode());
            }
            return accountMapper.insertSelective(newAccount);
        }
    }

    public int add(AddAccountVO addAccountVO) {
        BizAccount newAccount = new BizAccount();
        newAccount.setAccCode(addAccountVO.getAccCode());
        newAccount.setAmt(addAccountVO.getAmt());

        return accountMapper.insertSelective(newAccount);
    }

    @Transactional
    public int incrAmt(String accCode, Double incrAmt) {
        BizAccount bizAccount = Optional.ofNullable(accountMapper.selectOneForUpdate(accCode))
                .orElseThrow(() -> new RuntimeException("acc invalid. " + accCode));

        bizAccount.setAmt(bizAccount.getAmt() + incrAmt);
        return accountMapper.updateByPrimaryKey(bizAccount);
    }


    public List<BizAccount> getByIdRange(int start, int end) {
        BizAccountExample example = new BizAccountExample();
        example.createCriteria().andIdBetween(start, end);
        return accountMapper.selectByExample(example);
    }

    public List<BizAccount> getByAccCodes(List<String> accCodes) {
        BizAccountExample example = new BizAccountExample();
        example.createCriteria().andAccCodeIn(accCodes);
        return accountMapper.selectByExample(example);
    }

    public List<BizAccount> getByIds(List<Integer> ids) {
        BizAccountExample example = new BizAccountExample();
        example.createCriteria().andIdIn(ids);
        return accountMapper.selectByExample(example);
    }
}
