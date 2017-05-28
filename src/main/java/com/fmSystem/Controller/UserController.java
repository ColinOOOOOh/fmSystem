package com.fmSystem.Controller;

import com.fmSystem.Bean.Po.UserPo;
import com.fmSystem.Bean.Vo.LoginVo;
import com.fmSystem.Service.IUserService;
import com.fmSystem.Utility.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static java.util.logging.Level.WARNING;

/**
 * Created by 74551 on 2017/5/27.
 */
@Controller
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private IUserService userService;
    Logger logger = Logger.getLogger("userController");

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public ReturnMessage login(@RequestBody LoginVo loginVo){

        logger.log(WARNING,"fuck");
        ReturnMessage returnMessage = userService.login(loginVo);
        return  returnMessage;
    }
}
