package com.fmSystem.Algorithm.PoissonBasedWarehouse;

import com.fmSystem.Bean.Po.AvailableDatePo;
import com.fmSystem.Bean.Po.SalesRecordPo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 74551 on 2017/5/18.
 */
@Component("Warehouse")
public class Warehouse {


    public int getBestVol(double average_sales){
        int t = 1;
        BigDecimal big_fac_n, a;
        BigDecimal p = new BigDecimal(0);
        BigDecimal pv = new BigDecimal(0.95);
        BigDecimal e_lambda_t = new BigDecimal(Math.pow(Math.E, -average_sales * t));
        BigDecimal big_lambda = new BigDecimal(average_sales);
        BigDecimal big_t = new BigDecimal(t);
        int i = 1;
        BigDecimal b = new BigDecimal(500);
        //poisson
        if (-1 == big_lambda.multiply(big_t).compareTo(b)){
            System.out.println("poison");
            for (; -1 == p.compareTo(pv); i++ ){
                BigDecimal expectation = big_lambda.multiply(big_t).pow(i);
                big_fac_n = new BigDecimal(1);
                //fac
                for (int k = i; k > 0; k--){
                    a = new BigDecimal(k);
                    big_fac_n = big_fac_n.multiply(a);
                }
                p = expectation.multiply(e_lambda_t).divide(big_fac_n,1000, BigDecimal.ROUND_HALF_EVEN).add(p);
            }
        }else {
            //normal
            double n_p = 0;
            System.out.println("normal");
            for (;n_p < 0.95; i++){
                double a1 = 1 / (Math.sqrt(2 * Math.PI * average_sales * t));
                double a2 = Math.exp(-Math.pow(i - average_sales * t, 2) / (2 * average_sales * t));
                n_p = a1 * a2 + n_p;

            }


        }


        return i - 1;
    }

    public double laplace_correcting(int a, int m){
        return (double)1 / (double)(a + m);
    }

    public double calculate_average_sales(List<AvailableDatePo> avilable_date, List<SalesRecordPo> sales, int commodityId){
        HashMap<AvailableDatePo,Integer> map = new HashMap<AvailableDatePo, Integer>();
        for (Iterator<AvailableDatePo> iterator = avilable_date.iterator(); iterator.hasNext();){
            map.put(iterator.next(), 0);
        }

        Calendar calendar = Calendar.getInstance();
        for (Iterator iterator = sales.iterator(); iterator.hasNext();){
            SalesRecordPo po = (SalesRecordPo)iterator.next();
            if (po.getCommodityId() != commodityId) continue;
            //make key
            AvailableDatePo availableDatePo = new AvailableDatePo();
            calendar.setTime(po.getDate());
            availableDatePo.setYear(calendar.get(Calendar.YEAR));
            availableDatePo.setMonth(calendar.get(Calendar.MONTH)+1);
            int oldNum = map.get(availableDatePo);
            map.remove(availableDatePo);
            map.put(availableDatePo, oldNum+ po.getNumber());
        }
        //cal average
        int monthsNum = 0;
        int sumNum = 0;
        int averageSales;
        //cal average sales per month
        for (Iterator<Map.Entry<AvailableDatePo,Integer>> iterator = map.entrySet().iterator(); iterator.hasNext();){
            Map.Entry<AvailableDatePo,Integer> entry = iterator.next();
            sumNum = sumNum + entry.getValue();
            monthsNum++;
        }
        if (monthsNum == 0){
            return 0;
        }
        averageSales = sumNum / monthsNum;
        //cal p
//        int equalTimes = 0;
//        //for laplace correcting
//        int maxSales = 0;
//        double p = 0;
//        for (Iterator<Map.Entry<AvailableDatePo,Integer>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
//            Map.Entry<AvailableDatePo, Integer> entry = iterator.next();
//            if (entry.getValue() == averageSales){
//                equalTimes ++;
//            }
//            if (entry.getValue() > maxSales){
//                maxSales = entry.getValue();
//            }
//        }
//        p = (double)equalTimes / (double) monthsNum;
//        double e = 1e-7;
//        if (p < e){
//            p = laplace_correcting(monthsNum, maxSales);
//        }
        return averageSales;
    }


}
