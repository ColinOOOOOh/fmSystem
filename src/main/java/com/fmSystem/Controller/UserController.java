package com.fmSystem.Controller;

import com.fmSystem.Bean.Po.UserPo;
import com.fmSystem.Service.IUserService;
import com.fmSystem.Utility.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReturnMessage login(@RequestBody UserPo userPo){

        ReturnMessage returnMessage = userService.login(userPo);
        return  returnMessage;
    }
}
