package com.fmSystem.Service;

import com.fmSystem.Bean.Po.AvailableDatePo;
import com.fmSystem.Bean.Po.ShopPo;

import java.util.HashMap;

/**
 * Created by 74551 on 2017/4/28.
 */
public interface IShopService {
    public HashMap<AvailableDatePo,Double> getIncomePerMonth(int shopId);
}
