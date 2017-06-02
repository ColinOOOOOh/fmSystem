package com.fmSystem.Service;

import com.fmSystem.Bean.Po.UserPo;
import com.fmSystem.Bean.Vo.LoginVo;
import com.fmSystem.Bean.Vo.RegisterVo;
import com.fmSystem.Utility.ReturnMessage;

/**
 * Created by 74551 on 2017/4/22.
 */
public interface IUserService {
    ReturnMessage login(LoginVo loginVo);
    ReturnMessage register(RegisterVo registerVo);
    ReturnMessage userInfoModify(UserPo userPo);
    void setUserPermission(int userId, int shopId, String permission);

    ReturnMessage getUserInfo(int userId);
}
