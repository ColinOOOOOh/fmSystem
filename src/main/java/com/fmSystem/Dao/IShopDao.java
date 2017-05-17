package com.fmSystem.Dao;


import com.fmSystem.Bean.Po.SalesRecordPo;

import java.util.List;

/**
 * Created by 74551 on 2017/4/28.
 */
public interface IShopDao {
    public List<SalesRecordPo> getShopSalesRecord(int ShopId);
}
