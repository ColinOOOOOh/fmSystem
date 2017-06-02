package com.fmSystem.Dao;


import com.fmSystem.Bean.Po.SalesRecordPo;
import com.fmSystem.Bean.Po.ShopPo;
import com.fmSystem.Bean.Po.UserPo;

import java.util.List;

/**
 * Created by 74551 on 2017/4/28.
 */
public interface IShopDao {
    List<SalesRecordPo> getShopSalesRecord(int ShopId);

    void newShop(ShopPo shopPo);

    List<Integer> getEmployeeUserIdListByShopId(int shopId);

    UserPo searchEmployeeUser(String userPhone);


}
