package com.fmSystem.algorithmTest;

import com.fmSystem.Algorithm.PoissonBasedWarehouse.Warehouse;
import com.fmSystem.Bean.Po.AvailableDatePo;
import com.fmSystem.Bean.Po.SalesRecordPo;
import com.fmSystem.Dao.IAvailableDateDao;
import com.fmSystem.Dao.IShopDao;
import com.fmSystem.baseTest.SpringTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 74551 on 2017/5/18.
 */
public class warehouseTest extends SpringTestCase {
    @Autowired
    private IShopDao shopDao;
    @Autowired
    private IAvailableDateDao availableDateDao;
    @Autowired
    private Warehouse warehouse;
    @Test
    public void getBestVolTest(){

//        System.out.println("平均销量=10  " + "  库存="+ warehouse.getBestVol(10));
//        System.out.println("平均销量=20" +"  库存="+ warehouse.getBestVol(20));
//        System.out.println("平均销量=50" +"  库存="+ warehouse.getBestVol(50));
//        System.out.println("平均销量=50.5" + "  库存="+warehouse.getBestVol(50.5));
//        System.out.println("平均销量=100" + "  库存="+warehouse.getBestVol(100));
        System.out.println("平均销量=700" + "  库存="+warehouse.getBestVol(20));
    }

    @Test
    public void calculate_p_average_sales_test(){
        int shopId = 1;
        int commodityId = 2;
        List<AvailableDatePo> availableDateList = availableDateDao.getDateByShopId(shopId);
        List<SalesRecordPo> resultList = shopDao.getShopSalesRecord(shopId);
        double average_sales = warehouse.calculate_average_sales(availableDateList, resultList,commodityId);
        System.out.println(average_sales);
    }

}
