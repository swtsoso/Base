package com.voting.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.db.dao.user.UserLoginMapper;
import com.voting.service.user.UserLoginService;

/**
 * 请写出类的用途 
 * @author peng.wang
 * @date  2017-11-14 22:09:25
 * @version 1.0.0
 */
@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService{
    @Autowired
    private UserLoginMapper userLoginMapper;
 
}