package com.fmSystem.Service;

import com.fmSystem.Bean.Po.AvailableDatePo;
import com.fmSystem.Bean.Po.CommodityPo;
import com.fmSystem.Bean.Po.ShopPo;
import com.fmSystem.Bean.Vo.CommodityVo;
import com.fmSystem.Bean.Vo.MonthlySalesVo;
import com.fmSystem.Bean.Vo.YearMonthVo;
import com.fmSystem.Utility.ReturnMessage;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 74551 on 2017/4/28.
 */
public interface IShopService {
    ReturnMessage getMonthlySales(int shopId, YearMonthVo yearMonthVo);

    int newShop();

    ReturnMessage uploadRecordFile(File recordFile, int shopId, YearMonthVo yearMonthVo);

    ReturnMessage addEmployee(int userId, int shopId);

    ReturnMessage removeEmployee(int userId, int shopId);

    ReturnMessage searchEmployeeUser(String userPhone);

    ReturnMessage getEmployeeList(int shopId);

    ReturnMessage modifyShopInfoOnload(int shopId);

    ReturnMessage modifyShopInfo(List<CommodityPo> commodityPos, int shopId);
}
