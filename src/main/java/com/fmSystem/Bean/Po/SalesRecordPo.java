package com.fmSystem.Bean.Po;

import java.sql.Date;
import java.sql.Time;
import java.util.Timer;

/**
 * Created by 74551 on 2017/4/28.
 */
public class SalesRecordPo {
    private int salesRecordId;
    private int shopId;

    public int getSalesRecordId() {
        return salesRecordId;
    }

    public void setSalesRecordId(int salesRecordId) {
        this.salesRecordId = salesRecordId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString(){
        return "salesRecordId: " + salesRecordId + " shopId: " + shopId + " date: ";
    }
}
