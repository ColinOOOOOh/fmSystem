package com.fmSystem.Dao;

import com.fmSystem.Bean.Po.TestPo;

/**
 * Created by 74551 on 2017/4/13.
 */
public interface ITestDao {
    public TestPo selectUserById(int userId);
}
