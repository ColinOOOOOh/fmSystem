package com.fmSystem.Dao;

import com.fmSystem.Bean.Po.AvailableDatePo;

import java.util.List;

/**
 * Created by 74551 on 2017/4/29.
 */
public interface IAvailableDateDao {
    public List<AvailableDatePo> getDateByShopId(int shopId);

    void setAvailableDate(AvailableDatePo availableDate);
}
