package com.fmSystem.Service.Impl;

import com.fmSystem.Bean.Po.CommodityPo;
import com.fmSystem.Bean.Po.RecordInfoPo;
import com.fmSystem.Bean.Po.SalesRecordPo;
import com.fmSystem.Bean.Vo.RecordVo;
import com.fmSystem.Bean.Vo.SalesRecordVo;
import com.fmSystem.Bean.Vo.YearMonthVo;
import com.fmSystem.Dao.ICommodityDao;
import com.fmSystem.Dao.IRecordInfoDao;
import com.fmSystem.Dao.ISalesRecordDao;
import com.fmSystem.Service.IInTimeService;
import com.fmSystem.Utility.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 74551 on 2017/6/2.
 */
@Service
public class InTimeServiceImpl implements IInTimeService {

    @Autowired
    ISalesRecordDao salesRecordDao;
    @Autowired
    IRecordInfoDao recordInfoDao;
    @Autowired
    ICommodityDao commodityDao;
    @Override
    public ReturnMessage addSalesRecordAndInfo(int shopId, RecordVo recordVo) {
        ReturnMessage returnMessage = new ReturnMessage();
        //先在t_sales_record添加一行
        SalesRecordPo salesRecordPo = new SalesRecordPo();
        salesRecordPo.setShopId(shopId);
        salesRecordDao.setSalesRecord(salesRecordPo);
        //再在t_record_info添加一行
        RecordInfoPo recordInfoPo = new RecordInfoPo();
        CommodityPo commodityPo = commodityDao.getCommodityByNameAndWarehouseId(recordVo.getCommodityName(), shopId);
        recordInfoPo.setCommodityId(commodityPo.getCommodityId());
        recordInfoPo.setNumber(recordVo.getNumber());
        recordInfoPo.setSalesRecordId(salesRecordPo.getSalesRecordId());

         try{
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            java.util.Date utilDate = simpleDateFormat1.parse(recordVo.getrDate() + " " + recordVo.getrTime());
            java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            recordInfoPo.setDate(sqlDate);
            recordInfoPo.setTime(sqlTime);
        }catch (ParseException e){
            e.printStackTrace();
            returnMessage.setFlat(false);
        }
        recordInfoDao.setRecord(recordInfoPo);
        returnMessage.setFlat(true);
        returnMessage.setObject(salesRecordPo.getSalesRecordId());
        returnMessage.setMessageType("salesRecordId");
        return returnMessage;
    }

    @Override
    public ReturnMessage addRecordInfo(int shopId, RecordVo recordVo, int salesRecordId) {
        ReturnMessage returnMessage = new ReturnMessage();

        RecordInfoPo recordInfoPo = new RecordInfoPo();
        recordInfoPo.setCommodityId(recordVo.getCommodityId());
        recordInfoPo.setNumber(recordVo.getNumber());
        recordInfoPo.setSalesRecordId(salesRecordId);
        CommodityPo commodityPo = commodityDao.getCommodityByNameAndWarehouseId(recordVo.getCommodityName(), shopId);
        recordInfoPo.setCommodityId(commodityPo.getCommodityId());
        try{
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            java.util.Date utilDate = simpleDateFormat1.parse(recordVo.getrDate() + " " + recordVo.getrTime());
            java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            recordInfoPo.setDate(sqlDate);
            recordInfoPo.setTime(sqlTime);
        }catch (ParseException e){
            e.printStackTrace();
            returnMessage.setFlat(false);
        }
        recordInfoDao.setRecord(recordInfoPo);
        returnMessage.setFlat(true);
        returnMessage.setObject(salesRecordId);
        returnMessage.setMessageType("salesRecordId");
        return returnMessage;
    }

    @Override
    public ReturnMessage getRecordList(int shopId) {
        ReturnMessage returnMessage = new ReturnMessage();
        List<RecordInfoPo> sumList = new ArrayList<>();
        //获取所有销售记录号
        List<SalesRecordPo> salesRecordPoList = salesRecordDao.getSalesRecordsByShopId(shopId);
        for (int i = 0; i < salesRecordPoList.size(); i++){
            //用每个销售记录号检索记录详细信息
            SalesRecordPo salesRecordPo = salesRecordPoList.get(i);
            List<RecordInfoPo> recordInfoPoList = recordInfoDao.getRecordInfosBySalesRecordId(salesRecordPo.getSalesRecordId());
            sumList.addAll(recordInfoPoList);
        }
        List<RecordVo> returnList = new ArrayList<>();
        //po -> vo
        for (int i = 0; i < sumList.size(); i++){
            RecordInfoPo recordInfoPo = sumList.get(i);
            RecordVo recordVo = new RecordVo();
            CommodityPo commodityPo = commodityDao.getCommodityById(recordInfoPo.getCommodityId());
            recordVo.setCommodityName(commodityPo.getCommodityName());
            recordVo.setNumber(recordInfoPo.getNumber());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            recordVo.setrDate(sdf.format(recordInfoPo.getDate()));
            recordVo.setrTime(recordInfoPo.getTime().toLocalTime().toString());
            recordVo.setRecordInfoId(recordInfoPo.getRecordInfoId());
            returnList.add(recordVo);
        }
        returnMessage.setObject(returnList);
        returnMessage.setMessageType("List<RecordVp>");
        returnMessage.setFlat(true);
        return returnMessage;
    }

//    @Override
//    public ReturnMessage modifyRecord(int shopId, RecordVo recordVo) {
//        ReturnMessage returnMessage = new ReturnMessage();
//        RecordInfoPo recordInfoPo = new RecordInfoPo();
//        recordInfoPo.setCommodityId(recordVo.getCommodityId());
//        recordInfoPo.setNumber(recordVo.getNumber());
//
//        try{
//            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            java.util.Date utilDate = simpleDateFormat1.parse(recordVo.getrDate() + " " + recordVo.getrTime());
//            java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
//            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//            recordInfoPo.setDate(sqlDate);
//            recordInfoPo.setTime(sqlTime);
//        }catch (ParseException e){
//            e.printStackTrace();
//            returnMessage.setFlat(false);
//        }
//        recordInfoDao.updateRecord(recordInfoPo);
//        returnMessage.setFlat(true);
//        returnMessage.setContent("修改成功");
//        return returnMessage;
//    }

    @Override
    public ReturnMessage deleteRecord(int shopId, RecordVo recordVo) {
        ReturnMessage returnMessage = new ReturnMessage();
        recordInfoDao.deleteRecord(recordVo.getRecordInfoId());
        returnMessage.setFlat(true);
        returnMessage.setContent("删除成功");
        return returnMessage;
    }


}
