package com.lyx.ganon.mybatis.service;

import com.lyx.ganon.mybatis.constant.CashType;
import com.lyx.ganon.mybatis.mapper.BizUserMapper;
import com.lyx.ganon.mybatis.model.BizUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private BizUserMapper userMapper;

    @Autowired
    private AccountService accountService;

    public int createUser(BizUser user) {
        userMapper.insertSelective(user);
        return user.getId();
    }

    public void deleteAll() {
        userMapper.deleteByExample(null);
    }

    public int transMoney(int fromUserId, int toUserId, double amt, CashType cashType, int bizId, String remark) {
        return accountService.transUserMoney(fromUserId, toUserId, amt, cashType, bizId, remark);
    }
}
