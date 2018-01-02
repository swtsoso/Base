package com.voting.db.dao.user;

import org.springframework.stereotype.Repository;

import com.voting.model.user.UserLogin;

/**
 * 请写出类的用途 
 * @author peng.wang
 * @date  2017-11-14 22:09:25
 * @version 1.0.0
 * 
 */
@Repository
public interface UserLoginMapper {
    int delete(Long id);

    int insertSel(UserLogin record);

    UserLogin get(Long id);

    int updateById(UserLogin record);

    int update(UserLogin record);
}