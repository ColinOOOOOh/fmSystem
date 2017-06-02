package com.fmSystem.Dao;

import com.fmSystem.Bean.Po.CommodityPo;

import java.util.List;

/**
 * Created by 74551 on 2017/4/28.
 */
public interface ICommodityDao {
    CommodityPo getCommodityById(int commodityId);
    CommodityPo getCommodityByNameAndWarehouseId(String name, int warehouseId);
    void setCommodity(CommodityPo commodityPo);

    void updateCommodity(CommodityPo e);

    List<CommodityPo> getCommodityByWarehouseId(int warehouseId);
}
