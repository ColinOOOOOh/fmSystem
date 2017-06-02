package com.fmSystem.Dao;

import com.fmSystem.Bean.Po.RecordInfoPo;
import com.fmSystem.Bean.Vo.RecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 74551 on 2017/5/30.
 */
public interface IRecordInfoDao {
    RecordInfoPo getRecordInfoById(int infoId);
    List<RecordInfoPo> getRecordInfosBySalesRecordId(int recordId);
    List<Integer> getRecordIdsByComId(int commodityId);
    void setRecord(RecordInfoPo recordInfoPo);

    Integer getNumber(@Param("commodityId") int commodityId, @Param("year_month")String year_month, @Param("salesRecordId") int salesRecordId);

    void updateRecord(RecordInfoPo recordInfoPo);

    void deleteRecord(int recordInfoId);
}
