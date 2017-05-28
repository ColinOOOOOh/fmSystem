package com.fmSystem.serviceTest;

/**
 * Created by 74551 on 2017/4/15.
 */
import com.fmSystem.Bean.Po.TestPo;
import com.fmSystem.Bean.Vo.LoginVo;
import com.fmSystem.Service.ITestService;
import com.fmSystem.Service.IUserService;
import com.fmSystem.Utility.ReturnMessage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fmSystem.baseTest.SpringTestCase;



public class UserServiceTest extends SpringTestCase {
    @Autowired
    private ITestService testService;
    @Autowired
    private IUserService userService;

    Logger logger = Logger.getLogger(UserServiceTest.class);

    @Test
    public void selectUserByIdTest(){
        TestPo user = testService.selectUserById(1);
        logger.debug("查找结果" + user);
        logger.debug(user.getUsername());
    }

    @Test
    public void loginTest(){
        LoginVo loginVo1 = new LoginVo();
        LoginVo loginVo2 = new LoginVo();
        LoginVo loginVo3 = new LoginVo();
        loginVo1.setUsername("ouzhu");
        loginVo1.setUserPassword("123123");
        loginVo2.setUsername("ouz");
        loginVo2.setUserPassword("123133");
        loginVo3.setUsername("ouzhu");
        loginVo3.setUserPassword("123");
        ReturnMessage returnMessage;

        //returnMessage = userService.login(loginVo1);
        //Assert.assertEquals("登录成功",returnMessage.getContent());

        //returnMessage = userService.login(loginVo2);
        //Assert.assertEquals("该用户不存在",returnMessage.getContent());

        //returnMessage = userService.login(loginVo3);
        //Assert.assertEquals("密码错误",returnMessage.getContent());




    }

}
