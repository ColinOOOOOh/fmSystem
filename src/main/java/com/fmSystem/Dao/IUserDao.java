package com.fmSystem.Dao;

import com.fmSystem.Bean.Po.UserPo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 74551 on 2017/4/23.
 */
public interface IUserDao {
    UserPo getUserByPhone(@Param("userPhone") String userPhone);

    void userModifyInfo(UserPo userPo);

    void setUser(UserPo userPo);

    UserPo getUserById(int userId);
}
