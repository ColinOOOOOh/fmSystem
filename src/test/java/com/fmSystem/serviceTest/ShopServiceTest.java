package com.fmSystem.serviceTest;

import com.fmSystem.Bean.Po.AvailableDatePo;
import com.fmSystem.Service.Impl.ShopServiceImpl;
import com.fmSystem.baseTest.SpringTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * Created by 74551 on 2017/4/29.
 */
public class ShopServiceTest extends SpringTestCase{

    @Autowired
    private ShopServiceImpl shopService;

    @Test
    public void availableDatePoTest(){
        HashMap<AvailableDatePo, Double> map = new HashMap<AvailableDatePo, Double>();
        AvailableDatePo availableDatePo1 = new AvailableDatePo();
        availableDatePo1.setMonth(12);
        availableDatePo1.setYear(2001);
        AvailableDatePo availableDatePo2 = new AvailableDatePo();
        availableDatePo2.setMonth(12);
        availableDatePo2.setYear(2001);
        map.put(availableDatePo1, 10.0);
        Double res = map.get(availableDatePo2);
        Assert.assertEquals(res,10.0, 0.1);
    }

    @Test
    public void getIncomePerMonthTest(){
        HashMap<AvailableDatePo, Double> map = shopService.getIncomePerMonth(1);
        System.out.println(map.toString());
    }
}
