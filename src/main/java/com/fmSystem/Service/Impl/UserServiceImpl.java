package com.fmSystem.Service.Impl;

import com.fmSystem.Bean.Vo.LoginVo;
import com.fmSystem.Dao.IUserDao;
import com.fmSystem.Bean.Po.UserPo;
import com.fmSystem.Service.IUserService;
import com.fmSystem.Utility.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 74551 on 2017/4/23.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userdao;
    private ReturnMessage returnMessage;

    public ReturnMessage login( UserPo userPo1 ) {
        UserPo userPo = userdao.getUserByUsername(userPo1.getUsername());
        returnMessage = new ReturnMessage();
        if(userPo == null){
            returnMessage.setContent("该用户不存在");
            returnMessage.setFlat(false);
            return returnMessage;
        }
        if(userPo1.getUserPassword().equals(userPo.getUserPassword())){
            userPo.toString();
            returnMessage.setContent("登录成功");
            returnMessage.setFlat(true);
            return returnMessage;
        }
        returnMessage.setContent("密码错误");
        returnMessage.setFlat(false);
        return returnMessage;
    }
}
