package com.lyx.ganon.mybatis.service;

import com.lyx.ganon.mybatis.mapper.BizUserMapper;
import com.lyx.ganon.mybatis.model.BizUser;
import com.lyx.ganon.mybatis.model.BizUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private BizUserMapper userMapper;

    public Integer addUser(BizUser user) {
        userMapper.insertSelective(user);
        return user.getId();
    }

    public void deleteAll() {
        BizUserExample example = new BizUserExample();
        userMapper.deleteByExample(example);
    }
}
