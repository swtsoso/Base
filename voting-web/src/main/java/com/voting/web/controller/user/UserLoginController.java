package com.voting.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voting.manager.user.UserLoginManager;

/**
 * 请写出类的用途 
 * @author peng.wang
 * @date  2017-11-14 22:09:25
 * @version 1.0.0
 */
@Controller
@RequestMapping("/user_login")
public class UserLoginController {
    @Autowired
    private UserLoginManager userLoginManager;

    
}