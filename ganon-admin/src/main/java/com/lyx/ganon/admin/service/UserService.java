package com.lyx.ganon.admin.service;

import com.lyx.ganon.admin.controller.request.CreateUserReq;
import com.lyx.ganon.admin.mapper.SysUserMapper;
import com.lyx.ganon.admin.model.SysUser;
import com.lyx.ganon.admin.model.SysUserExample;
import com.lyx.ganon.common.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

/**
 * @Description UserServiceImpl
 * @Author liyuxing
 * @Date 2019-12-13
 */
@Service
public class UserService {

    @Autowired
    private SysUserMapper userMapper;

    public void createUser(CreateUserReq req) {
        SysUser user = new SysUser();
        user.setName(req.getName());
        user.setPassword(req.getPassword());
        user.setPasswordMd5(DigestUtils.getMD5(req.getPassword()));
        user.setPasswordSha(DigestUtils.getSHA1(req.getPassword()));
        userMapper.insertSelective(user);
    }

    @Nullable
    public SysUser getUser(String name) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andNameEqualTo(name);
        return userMapper.selectByExample(example).stream()
                .findFirst().orElse(null);
    }
}
