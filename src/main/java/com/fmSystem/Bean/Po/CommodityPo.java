package com.fmSystem.Bean.Po;

/**
 * Created by 74551 on 2017/4/22.
 */
public class CommodityPo {
    private  int warehouseId;
    private int commodityId;
    private  String commodityName;
    private double commodityPrice;
    private  double commodityCost;


    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
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



    @Override
    public String toString() {
        return "warehouseId: " +  warehouseId +  " commodityId: " + commodityId + " commodityName: "
                + commodityName + " commodityPrice: " + commodityPrice + " commodityCost: " + commodityCost;
    }
}
