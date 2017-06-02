package com.fmSystem.Service.Impl;

import com.fmSystem.Algorithm.PoissonBasedWarehouse.Warehouse;
import com.fmSystem.Bean.Po.*;
import com.fmSystem.Bean.Vo.MonthlySalesVo;
import com.fmSystem.Bean.Vo.SalesRecordVo;
import com.fmSystem.Bean.Vo.YearMonthVo;
import com.fmSystem.Dao.*;
import com.fmSystem.Service.IShopService;
import com.fmSystem.Service.IWarehouseService;
import com.fmSystem.Utility.ExcelLoador;
import com.fmSystem.Utility.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.*;
import java.sql.Date;
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
    @Autowired
    private ISalesRecordDao salesRecordDao;
    @Autowired
    private Warehouse warehouse;
    @Autowired
    private ExcelLoador excelLoador;
    @Autowired
    private IWarehouseDao warehouseDao;
    @Autowired
    private IRecordInfoDao recordInfoDao;
    @Autowired
    private IWarehouseService warehouseService;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IPermissionDao permissionDao;
/*
    public HashMap<AvailableDatePo,Double> geMonthlySales(int shopId){

        List<SalesRecordPo> resultList = shopDao.getShopSalesRecord(shopId);
        HashMap<AvailableDatePo,Double> map = new HashMap<AvailableDatePo, Double>();

        List<AvailableDatePo> availableDateList = availableDateDao.getDateByShopId(shopId);
        for (Iterator iterator = availableDateList.iterator(); iterator.hasNext();){
            map.put((AvailableDatePo)iterator.next(), 0.0);
        }
        RecordInfoPo recordInfoPo;
        CommodityPo commodityPo;
        java.sql.Date date;
        Calendar calendar = Calendar.getInstance();
        AvailableDatePo adInRecord;



        for(Iterator iterator = resultList.iterator();iterator.hasNext();){
            recordInfoPo = (RecordInfoPo)iterator.next();
            date = recordInfoPo.getrDate();
            calendar.setTime(date);
            adInRecord = new AvailableDatePo();
            adInRecord.setYear(calendar.get(Calendar.YEAR));
            adInRecord.setMonth(calendar.get(Calendar.MONTH) + 1);

            commodityPo = commodityDao.getCommodityById(recordInfoPo.getCommodityId());
            //每次交易收益=（售价-成本）售出件数
            double profitPerCom = commodityPo.getCommodityPrice() - commodityPo.getCommodityCost();
            double profit = (double)recordInfoPo.getNumber() * profitPerCom;

            double sumProfit = map.get(adInRecord);
            sumProfit = profit + sumProfit;
            map.remove(adInRecord);
            map.put(adInRecord, sumProfit);
        }

        return map;
    }
*/

    @Override
    public int newShop() {
        ShopPo shopPo = new ShopPo();
        shopPo.setShopName("商店");
        shopDao.newShop(shopPo);
        //new warehouse
        warehouseService.newWarehouse(shopPo.getShopId());
        return shopPo.getShopId();
    }



    public ReturnMessage uploadRecordFile(File recordFile, int shopId, YearMonthVo yearMonthVo){
        ReturnMessage returnMessage = new ReturnMessage();
        List<SalesRecordVo> recordVos = excelLoador.loadRecordFile(recordFile);
        if (recordVos == null){
            returnMessage.setFlat(false);
            returnMessage.setContent("上传失败");
            return returnMessage;
        }
        Date lastDate = null;
        Time lastTime = null;
        int salesRecordId = 0;
        for (Iterator<SalesRecordVo> it = recordVos.iterator();it.hasNext();){
            SalesRecordVo vo = it.next();
            SalesRecordPo salesRecordPo = new SalesRecordPo();
            salesRecordPo.setShopId(shopId);
            //s1
            if (!isSameDateAndTime(lastDate, vo.getrDate(), lastTime, vo.getTime())){
                salesRecordDao.setSalesRecord(salesRecordPo);
                lastDate = vo.getrDate();
                lastTime = vo.getTime();
                salesRecordId = salesRecordPo.getSalesRecordId();
            }
            //s2
            RecordInfoPo recordInfoPo = new RecordInfoPo();
            recordInfoPo.setSalesRecordId(salesRecordId);
            recordInfoPo.setNumber(vo.getNumber());
            recordInfoPo.setTime(vo.getTime());
            recordInfoPo.setDate(vo.getrDate());
            recordInfoPo.setCommodityId(getCommodityIdByName(vo.getCommodityName(),shopId));
            recordInfoDao.setRecord(recordInfoPo);
        }
        AvailableDatePo availableDatePo = new AvailableDatePo();
        availableDatePo.setYear(Integer.parseInt(yearMonthVo.getYear()));
        availableDatePo.setMonth(Integer.parseInt(yearMonthVo.getMonth()));
        availableDatePo.setShopId(shopId);
        availableDateDao.setAvailableDate(availableDatePo);
        returnMessage.setFlat(true);
        returnMessage.setContent("上传成功");
        return returnMessage;
    }

    @Override
    public ReturnMessage addEmployee(int userId, int shopId) {
        ReturnMessage returnMessage = new ReturnMessage();
        PermissionPo permissionPo = new PermissionPo("EMPLOYEE", shopId, userId);
        permissionDao.setPermission(permissionPo);
        returnMessage.setFlat(true);
        returnMessage.setContent("添加成功");
        return returnMessage;
    }

    @Override
    public ReturnMessage removeEmployee(int userId, int shopId) {
        ReturnMessage returnMessage = new ReturnMessage();
        permissionDao.deletePermission(shopId, userId);
        returnMessage.setFlat(true);
        returnMessage.setContent("删除成功");
        return returnMessage;
    }

    @Override
    public ReturnMessage searchEmployeeUser(String userPhone) {
        ReturnMessage returnMessage = new ReturnMessage();
        UserPo userPo = shopDao.searchEmployeeUser(userPhone);
        returnMessage.setObject(userPo);
        returnMessage.setFlat(true);
        returnMessage.setContent("查询成功");
        returnMessage.setMessageType("UserPo");
        return returnMessage;
    }

    @Override
    public ReturnMessage getEmployeeList(int shopId) {
        ReturnMessage returnMessage = new ReturnMessage();
        List<Integer> employeeUserIdList = shopDao.getEmployeeUserIdListByShopId(shopId);
        List<UserPo> employeeList = new ArrayList<>();
        employeeUserIdList.forEach((Integer e) -> {
            UserPo userPo = userDao.getUserById(e);
            userPo.setUserPassword(null);
            employeeList.add(userPo);
        });
        returnMessage.setObject(employeeList);
        returnMessage.setFlat(true);
        returnMessage.setContent("查询成功");
        returnMessage.setMessageType("List<UserPo>");
        if (employeeList == null){
            returnMessage.setContent("该店还没有任何用户");
        }
        return returnMessage;
    }

    @Override
    public ReturnMessage modifyShopInfoOnload(int shopId) {
        ReturnMessage returnMessage = new ReturnMessage();
        int warehouseId = warehouseDao.getWarehouseIdByShopId(shopId);
        List<CommodityPo> commodityPoList = commodityDao.getCommodityByWarehouseId(warehouseId);
        returnMessage.setObject(commodityPoList);
        returnMessage.setFlat(true);
        returnMessage.setContent("查询成功");
        returnMessage.setMessageType("List<CommodityPo>");
        if (commodityPoList == null){
            returnMessage.setContent("该店没有任何商品");
        }
        return returnMessage;
    }

    @Override
    public ReturnMessage modifyShopInfo(List<CommodityPo> commodityPos, int shopId) {
        ReturnMessage returnMessage = new ReturnMessage();
        commodityPos.forEach((CommodityPo e) -> {
            commodityDao.updateCommodity(e);
        });
        returnMessage.setFlat(true);
        returnMessage.setContent("修改成功");
        return returnMessage;
    }

    public ReturnMessage getMonthlySales(int shopId, YearMonthVo yearMonthVo) {
        int warehouseId = warehouseDao.getWarehouseIdByShopId(shopId);
        List<CommodityPo> commodityPoList = commodityDao.getCommodityByWarehouseId(warehouseId);
        List<MonthlySalesVo> monthlySalesVoList = new ArrayList<>();
        commodityPoList.forEach((CommodityPo e) -> {
            MonthlySalesVo monthlySalesVo = new MonthlySalesVo();
            monthlySalesVo.setCommodityName(e.getCommodityName());
            List<SalesRecordPo> salesRecordPoList = salesRecordDao.getSalesRecordsByShopId(shopId);
            salesRecordPoList.forEach((SalesRecordPo f) -> {
                Integer returnNumber = recordInfoDao.getNumber(e.getCommodityId(), yearMonthVo.toString(), f.getSalesRecordId());
                int number = 0;
                if (returnNumber != null){
                    number = returnNumber.intValue();
                }
                monthlySalesVo.setMonthlySales(monthlySalesVo.getMonthlySales() + number);
            });
            monthlySalesVoList.add(monthlySalesVo);
        });
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setObject(monthlySalesVoList);
        returnMessage.setMessageType("List<MonthlySalesVo>");
        returnMessage.setFlat(true);
        returnMessage.setContent("查找成功");
        return returnMessage;
    }

    private boolean isSameDateAndTime(java.sql.Date date1, java.sql.Date date2, java.sql.Time time1, java.sql.Time time2){
        if (date1 == null || date2 == null){
            return false;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDay = cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
        boolean isSameTime = time1.getTime() == time2.getTime();
        return isSameDay && isSameMonth && isSameYear && isSameTime;
    }

    private int getCommodityIdByName(String commodityName, int shopId){
        CommodityPo commodityPo = commodityDao.getCommodityByNameAndWarehouseId(commodityName, warehouseDao.getWarehouseIdByShopId(shopId));
        if (commodityPo == null){
            commodityPo = setCommodity(commodityName,shopId);
        }
        return commodityPo.getCommodityId();
    }

    private CommodityPo setCommodity(String commodityName, int shopId){
        CommodityPo commodityPo = new CommodityPo();
        commodityPo.setWarehouseId(warehouseDao.getWarehouseIdByShopId(shopId));
        commodityPo.setCommodityName(commodityName);
        commodityDao.setCommodity(commodityPo);
        return commodityPo;
    }


}
