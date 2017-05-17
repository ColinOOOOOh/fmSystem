package com.fmSystem.Service.Impl;

import com.fmSystem.Bean.Po.AvailableDatePo;
import com.fmSystem.Bean.Po.CommodityPo;
import com.fmSystem.Bean.Po.SalesRecordPo;
import com.fmSystem.Bean.Po.ShopPo;
import com.fmSystem.Dao.IAvailableDateDao;
import com.fmSystem.Dao.ICommodityDao;
import com.fmSystem.Dao.IShopDao;
import com.fmSystem.Service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 74551 on 2017/4/28.
 */
@Service
public class ShopServiceImpl implements IShopService{
    @Autowired
    private IShopDao shopDao;
    @Autowired
    private ICommodityDao commodityDao;
    @Autowired
    private IAvailableDateDao availableDateDao;
    public HashMap<AvailableDatePo,Double> getIncomePerMonth(int shopId){
        List<SalesRecordPo> resultList = shopDao.getShopSalesRecord(shopId);
        HashMap<AvailableDatePo,Double> map = new HashMap<AvailableDatePo, Double>();

        List<AvailableDatePo> availableDateList = availableDateDao.getDateByShopId(shopId);
        for (Iterator iterator = availableDateList.iterator(); iterator.hasNext();){
            map.put((AvailableDatePo)iterator.next(), 0.0);
        }
        SalesRecordPo salesRecordPo;
        CommodityPo commodityPo;
        java.sql.Date date;
        Calendar calendar = Calendar.getInstance();
        AvailableDatePo adInRecord;



        for(Iterator iterator = resultList.iterator();iterator.hasNext();){
            salesRecordPo = (SalesRecordPo)iterator.next();
            date = salesRecordPo.getDate();
            calendar.setTime(date);
            adInRecord = new AvailableDatePo();
            adInRecord.setYear(calendar.get(Calendar.YEAR));
            adInRecord.setMonth(calendar.get(Calendar.MONTH) + 1);

            commodityPo = commodityDao.getCommodityById(salesRecordPo.getCommodityId());
            //每次交易收益=（售价-成本）售出件数
            double profitPerCom = commodityPo.getCommodityPrice() - commodityPo.getCommodityCost();
            double profit = (double)salesRecordPo.getNumber() * profitPerCom;

            double sumProfit = map.get(adInRecord);
            sumProfit = profit + sumProfit;
            map.remove(adInRecord);
            map.put(adInRecord, sumProfit);
        }

        return map;
    }
}
