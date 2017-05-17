package com.fmSystem.Dao;

import com.fmSystem.Bean.Po.UserPo;

/**
 * Created by 74551 on 2017/4/23.
 */
public interface IUserDao {
    public UserPo getUserByUsername(String username);
}
