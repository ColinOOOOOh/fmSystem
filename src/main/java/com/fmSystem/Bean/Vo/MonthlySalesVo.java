package com.fmSystem.Bean.Vo;

/**
 * Created by 74551 on 2017/6/1.
 */
public class MonthlySalesVo {
    private int monthlySales;
    private String commodityName;

    public MonthlySalesVo() {
    }

    public int getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(int monthlySales) {
        this.monthlySales = monthlySales;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
}
