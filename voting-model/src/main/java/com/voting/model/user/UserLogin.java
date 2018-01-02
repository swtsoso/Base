package com.voting.model.user;

import java.io.Serializable;
import java.util.Date;

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
public class UserLogin implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 密钥
     */
    private Long salt;

    /**
     * 登录名
     */
    private String loginname;

    /**
     * 登陆时间
     */
    private Date loginTime;

    /**
     * 
     */
    private String loginIp;

    /**
     * 
     */
    private Byte status;

    /**
     * 
     */
    private Long updateId;

    /**
     * 
     */
    private String updateName;

    /**
     * 
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 
     * {@linkplain #id}
     *
     * @return the value of user_login.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * {@linkplain #id}
     * @param id the value for user_login.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * {@linkplain #userName}
     *
     * @return the value of user_login.user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * {@linkplain #userName}
     * @param userName the value for user_login.user_name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * {@linkplain #password}
     *
     * @return the value of user_login.password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * {@linkplain #password}
     * @param password the value for user_login.password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * {@linkplain #salt}
     *
     * @return the value of user_login.salt
     */
    public Long getSalt() {
        return salt;
    }

    /**
     * 
     * {@linkplain #salt}
     * @param salt the value for user_login.salt
     */
    public void setSalt(Long salt) {
        this.salt = salt;
    }

    /**
     * 
     * {@linkplain #loginname}
     *
     * @return the value of user_login.loginname
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * 
     * {@linkplain #loginname}
     * @param loginname the value for user_login.loginname
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    /**
     * 
     * {@linkplain #loginTime}
     *
     * @return the value of user_login.login_time
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 
     * {@linkplain #loginTime}
     * @param loginTime the value for user_login.login_time
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 
     * {@linkplain #loginIp}
     *
     * @return the value of user_login.login_ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 
     * {@linkplain #loginIp}
     * @param loginIp the value for user_login.login_ip
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 
     * {@linkplain #status}
     *
     * @return the value of user_login.status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 
     * {@linkplain #status}
     * @param status the value for user_login.status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 
     * {@linkplain #updateId}
     *
     * @return the value of user_login.update_id
     */
    public Long getUpdateId() {
        return updateId;
    }

    /**
     * 
     * {@linkplain #updateId}
     * @param updateId the value for user_login.update_id
     */
    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    /**
     * 
     * {@linkplain #updateName}
     *
     * @return the value of user_login.update_name
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * 
     * {@linkplain #updateName}
     * @param updateName the value for user_login.update_name
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    /**
     * 
     * {@linkplain #updateTime}
     *
     * @return the value of user_login.update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * {@linkplain #updateTime}
     * @param updateTime the value for user_login.update_time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}