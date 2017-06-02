package com.fmSystem.Bean.Vo;

/**
 * Created by 74551 on 2017/6/1.
 */
public class CommodityVo {


    private  int commodityId;
    private  String commodityName;
    private double commodityPrice;
    private  double commodityCost;

    public CommodityVo() {
    }
    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }
    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public double getCommodityCost() {
        return commodityCost;
    }

    public void setCommodityCost(double commodityCost) {
        this.commodityCost = commodityCost;
    }
}
