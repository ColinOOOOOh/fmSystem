package com.fmSystem.Service;

import com.fmSystem.Bean.Vo.RecordVo;
import com.fmSystem.Bean.Vo.SalesRecordVo;
import com.fmSystem.Bean.Vo.YearMonthVo;
import com.fmSystem.Utility.ReturnMessage;

/**
 * Created by 74551 on 2017/6/1.
 */
public interface IInTimeService {

    ReturnMessage addSalesRecordAndInfo(int shopId, RecordVo recordVo);

    ReturnMessage addRecordInfo(int shopId, RecordVo recordVo, int salesRecordId);

    ReturnMessage getRecordList(int shopId);

    //ReturnMessage modifyRecord(int shopId, RecordVo recordVo);

    ReturnMessage deleteRecord(int shopId, RecordVo recordVo);
}
