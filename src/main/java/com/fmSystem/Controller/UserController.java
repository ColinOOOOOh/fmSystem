package com.fmSystem.Controller;

import com.fmSystem.Bean.Po.PermissionPo;
import com.fmSystem.Bean.Po.UserPo;
import com.fmSystem.Bean.Vo.LoginVo;
import com.fmSystem.Bean.Vo.RegisterVo;
import com.fmSystem.Service.IUserService;
import com.fmSystem.Utility.ReturnMessage;
import org.omg.CORBA.PERSIST_STORE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public ReturnMessage login(@RequestBody LoginVo loginVo, HttpSession httpSession){

        ReturnMessage returnMessage = userService.login(loginVo);
        PermissionPo permissionPo = (PermissionPo)returnMessage.getObject();
        if (permissionPo == null && returnMessage.isFlat() == true){
            returnMessage.setContent("还没有老板要你哦");
            returnMessage.setFlat(false);
            httpSession.setAttribute("SHOP_ID", null);
            httpSession.setAttribute("PERMISSION", null);
            httpSession.setAttribute("USER_ID", null);
            return returnMessage;
        }
        if (returnMessage.isFlat() == true){
            httpSession.setAttribute("SHOP_ID", permissionPo.getShopId());
            httpSession.setAttribute("PERMISSION", permissionPo.getPermission());
            httpSession.setAttribute("USER_ID", permissionPo.getUserId());
        }

        return  returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public ReturnMessage register(@RequestBody RegisterVo registerVo){
        ReturnMessage returnMessage = userService.register(registerVo);
        return  returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/userInfoModify", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public ReturnMessage userInfoModify(@RequestBody UserPo userPo){
        ReturnMessage returnMessage = userService.userInfoModify(userPo);
        return  returnMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/userInfoModifyOnload", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public ReturnMessage userInfoModifyOnload(HttpSession httpSession){
        int userId = (int)httpSession.getAttribute("USER_ID");
        ReturnMessage returnMessage = userService.getUserInfo(userId);
        return  returnMessage;
    }
}
