package com.fmSystem.Bean.Po;

/**
 * Created by 74551 on 2017/4/22.
 */
public class ShopPo {
    private int shopId;
    private String shopName;
    private int warehouseId;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public String toString(){
        return "shopId: "  + shopId + " shopName: " + shopName + " warehouseId + "+ warehouseId;
    }

}
