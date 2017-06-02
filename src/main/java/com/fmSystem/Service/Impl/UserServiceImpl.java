package com.fmSystem.Service.Impl;

import com.fmSystem.Bean.Po.PermissionPo;
import com.fmSystem.Bean.Vo.LoginVo;
import com.fmSystem.Bean.Vo.RegisterVo;
import com.fmSystem.Dao.IPermissionDao;
import com.fmSystem.Dao.IUserDao;
import com.fmSystem.Bean.Po.UserPo;
import com.fmSystem.Service.IShopService;
import com.fmSystem.Service.IUserService;
import com.fmSystem.Utility.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.registry.infomodel.User;

/**
 * Created by 74551 on 2017/4/23.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userdao;
    @Autowired
    private IShopService shopService;
    @Autowired
    private IPermissionDao permissionDao;


    public ReturnMessage login( LoginVo loginVo ) {
        UserPo userPo = userdao.getUserByPhone(loginVo.getUserPhone());
        ReturnMessage returnMessage = new ReturnMessage();
        if(userPo == null){
            returnMessage.setContent("该用户不存在");
            returnMessage.setFlat(false);
            return returnMessage;
        }
        if(loginVo.getUserPassword().equals(userPo.getUserPassword())){
            returnMessage.setContent("登录成功");
            returnMessage.setFlat(true);
            PermissionPo permissionPo = permissionDao.getPermissionPoByUserId(userPo.getUserId());
            returnMessage.setObject(permissionPo);
            returnMessage.setMessageType("PermissionPo");
            return returnMessage;
        }
        returnMessage.setContent("密码错误");
        returnMessage.setFlat(false);

        return returnMessage;
    }

    @Override
    public ReturnMessage register(RegisterVo registerVo) {
        ReturnMessage returnMessage = new ReturnMessage();
        UserPo tmpPo = userdao.getUserByPhone(registerVo.getUserPhone());
        if (tmpPo == null) {
            UserPo userPo = new UserPo(registerVo.getUsername(), registerVo.getUserPhone(),registerVo.getUserPassword());
            userdao.setUser(userPo);
            if (registerVo.getPermission().equals("OWNER")){
                int shopId = shopService.newShop();
                setUserPermission(userPo.getUserId(),shopId,"OWNER");
            }
            returnMessage.setContent("注册成功");
            returnMessage.setFlat(true);
            return returnMessage;
        }
        returnMessage.setContent("该用户已存在");
        returnMessage.setFlat(false);
        return returnMessage;
    }

    @Override
    public ReturnMessage userInfoModify(UserPo newUserPo) {
        ReturnMessage returnMessage = new ReturnMessage();
        UserPo userPo1 = userdao.getUserByPhone(newUserPo.getUserPhone());
        newUserPo.setUserId(userPo1.getUserId());
        userdao.userModifyInfo(newUserPo);
        returnMessage.setContent("修改成功");
        returnMessage.setFlat(true);
        return returnMessage;
    }

    @Override
    public void setUserPermission(int userId, int shopId, String permission) {
        PermissionPo permissionPo = new PermissionPo(permission, shopId, userId);
        permissionDao.setPermission(permissionPo);
    }

    @Override
    public ReturnMessage getUserInfo(int userId) {
        ReturnMessage returnMessage = new ReturnMessage();
        UserPo userPo = userdao.getUserById(userId);
        returnMessage.setFlat(true);
        returnMessage.setObject(userPo);
        return returnMessage;
    }


}
