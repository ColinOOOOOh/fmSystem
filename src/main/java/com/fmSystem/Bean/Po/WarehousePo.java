package com.fmSystem.Bean.Po;

/**
 * Created by 74551 on 2017/4/22.
 */
public class WarehousePo {
    private int warehouseId;
    private int shopId;

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }


    @Override
    public String toString(){
        return "warehouseId: " + warehouseId + " shopId: "+ shopId;
    }

}
