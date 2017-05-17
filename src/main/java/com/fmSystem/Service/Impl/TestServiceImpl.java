package com.fmSystem.Service.Impl;

import com.fmSystem.Dao.ITestDao;
import com.fmSystem.Bean.Po.TestPo;
import com.fmSystem.Service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 74551 on 2017/4/13.
 */
@Service("testService")
public class TestServiceImpl implements ITestService{

    @Autowired
    private ITestDao testDao;

    public TestPo selectUserById(int id) {
        TestPo testPo = testDao.selectUserById(id);

        return testPo;
    }
}
