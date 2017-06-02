package com.fmSystem.Dao;

import com.fmSystem.Bean.Po.WarehousePo;

/**
 * Created by 74551 on 2017/5/30.
 */
public interface IWarehouseDao {
    void setWarehouse(WarehousePo shopId);
    int getWarehouseIdByShopId(int shopId);
}
