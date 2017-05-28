package com.fmSystem.Service;

import com.fmSystem.Bean.Po.UserPo;
import com.fmSystem.Bean.Vo.LoginVo;
import com.fmSystem.Utility.ReturnMessage;

/**
 * Created by 74551 on 2017/4/22.
 */
public interface IUserService {
    ReturnMessage login(UserPo userPo);
}
