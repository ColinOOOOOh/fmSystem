package com.fmSystem.Dao;

import com.fmSystem.Bean.Po.RecordInfoPo;
import com.fmSystem.Bean.Po.SalesRecordPo;

import java.util.List;

/**
 * Created by 74551 on 2017/5/30.
 */
public interface ISalesRecordDao {
    void setSalesRecord(SalesRecordPo salesRecordPo);

    List<SalesRecordPo> getSalesRecordsByShopId(int shopId);
}
