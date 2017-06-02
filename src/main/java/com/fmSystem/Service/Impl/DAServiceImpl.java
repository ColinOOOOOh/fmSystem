package com.fmSystem.Service.Impl;

import com.fmSystem.Algorithm.AssociationLearner.miner.*;
import com.fmSystem.Algorithm.PoissonBasedWarehouse.Warehouse;
import com.fmSystem.Bean.Po.AvailableDatePo;
import com.fmSystem.Bean.Po.CommodityPo;
import com.fmSystem.Bean.Po.RecordInfoPo;
import com.fmSystem.Bean.Po.SalesRecordPo;
import com.fmSystem.Dao.*;
import com.fmSystem.Service.IDAService;
import com.fmSystem.Utility.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by 74551 on 2017/5/29.
 */
@Service
public class DAServiceImpl  implements IDAService{
    @Autowired
    private IShopDao shopDao;
    @Autowired
    private IAvailableDateDao availableDateDao;
    @Autowired
    private ICommodityDao commodityDao;
    @Autowired
    private IRecordInfoDao recordInfoDao;
    @Autowired
    private IWarehouseDao warehouseDao;
    @Autowired
    private Warehouse warehouse;
    @Autowired
    private ISalesRecordDao salesRecordDao;
    public ReturnMessage associationAnalysis(List<String> items, int shopId){
        ReturnMessage returnMessage = new ReturnMessage();
        List<Integer> commodityIdList = new ArrayList<>();
        final int warehouseId = warehouseDao.getWarehouseIdByShopId(shopId);
        items.forEach((String name) -> {
            CommodityPo commodityPo = commodityDao.getCommodityByNameAndWarehouseId(name, warehouseId);
            commodityIdList.add(commodityPo.getCommodityId());
        });
        FrequentPatternsFactory fact = new FrequentPatternsFactory(new IntKHash());
        InMemoryDataAccessor imda = new InMemoryDataAccessor(fact);
        commodityIdList.forEach((Integer e) -> {
            List<Integer> recordIdList = recordInfoDao.getRecordIdsByComId(e);
            recordIdList.forEach((Integer i) -> {
                List<RecordInfoPo> recordInfoPos = recordInfoDao.getRecordInfosBySalesRecordId(i);
                //create shop car
                List<Integer> comIdInRecord = new ArrayList<>();
                recordInfoPos.forEach((RecordInfoPo p) -> {
                    comIdInRecord.add(p.getCommodityId());
                });
                imda.add_transaction(new FrequentPattern(comIdInRecord,1));
            });
        });
        IPatternMiner miner = new FTSMiner();
        FrequentPatterns patterns = miner.mine(imda,0.05f, 0.6f, fact);
        Pattern all = new Pattern(commodityIdList);
        FrequentPatterns sub_patterns = patterns.match_subpattern_of(all);
        List<Integer[]> resList = new ArrayList<>();
        for (FrequentPattern p: sub_patterns){
            Integer[] a = (Integer[])p.toArray(new Integer[(p.size())]);
            resList.add(a);
        }
        List<String[]> returnList = new ArrayList<>();
        resList.forEach((Integer[] e) -> {
            if (e.length > 1){
                String[] comArray = new String[e.length];
                for (int i = 0; i < e.length; i++){
                    CommodityPo commodityPo = commodityDao.getCommodityById(e[i]);
                    comArray[i] = commodityPo.getCommodityName();
                }
                returnList.add(comArray);
            }

        });

        returnMessage.setObject(returnList);
        returnMessage.setFlat(true);
        returnMessage.setContent("List<String[]>");
        return returnMessage;
    }

    @Override
    public ReturnMessage warehouseCal(String commodityName, int shopId) {
        ReturnMessage returnMessage = new ReturnMessage();
        List<AvailableDatePo> availableDateList = availableDateDao.getDateByShopId(shopId);
        int warehouseId = warehouseDao.getWarehouseIdByShopId(shopId);
        CommodityPo commodityPo = commodityDao.getCommodityByNameAndWarehouseId(commodityName, warehouseId);

        List<RecordInfoPo> sumList = new ArrayList<>();
        //获取所有销售记录号
        List<SalesRecordPo> salesRecordPoList = salesRecordDao.getSalesRecordsByShopId(shopId);
        for (int i = 0; i < salesRecordPoList.size(); i++){
            //用每个销售记录号检索记录详细信息
            SalesRecordPo salesRecordPo = salesRecordPoList.get(i);
            List<RecordInfoPo> recordInfoPoList = recordInfoDao.getRecordInfosBySalesRecordId(salesRecordPo.getSalesRecordId());
            sumList.addAll(recordInfoPoList);
        }

        double average_sales = warehouse.calculate_average_sales(availableDateList, sumList, commodityPo.getCommodityId());
        int vol = warehouse.getBestVol(average_sales);
        returnMessage.setFlat(true);
        returnMessage.setObject(vol);
        return returnMessage;
    }

    @Override
    public ReturnMessage getCommodityList(int shopId) {
        ReturnMessage returnMessage = new ReturnMessage();
        int warehouseId = warehouseDao.getWarehouseIdByShopId(shopId);
        List<CommodityPo> commodityPoList = commodityDao.getCommodityByWarehouseId(warehouseId);
        returnMessage.setObject(commodityPoList);
        returnMessage.setFlat(true);
        returnMessage.setMessageType("List<CommodityPo>");
        return returnMessage;
    }

    @Override
    public ReturnMessage layout(int shopId) {
        return null;
    }


}
