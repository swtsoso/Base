package com.voting.manager.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.manager.user.UserLoginManager;
import com.voting.service.user.UserLoginService;

/**
 * 请写出类的用途 
 * @author peng.wang
 * @date  2017-11-14 22:09:25
 * @version 1.0.0
 * @copyright (C) 2015 XiPinTech Information Technology Co.,Ltd 
 * All Rights Reserved. 
 * 
 * The software for the XiPinTech technology development, without the 
 * company's written consent, and any other individuals and 
 * organizations shall not be used, Copying, Modify or distribute 
 * the software.
 * 
 */
@Service("userLoginManager")
public class UserLoginManagerImpl implements UserLoginManager{
    @Autowired
    private UserLoginService userLoginService;
   
}