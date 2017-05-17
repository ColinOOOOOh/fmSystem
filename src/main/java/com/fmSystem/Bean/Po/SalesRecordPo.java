package com.fmSystem.Bean.Po;

import java.sql.Date;
import java.sql.Time;
import java.util.Timer;

/**
 * Created by 74551 on 2017/4/28.
 */
public class SalesRecordPo {
    private int commodityId;
    private int salesRecordId;
    private int shopId;
    private Date date;
    private Time time;
    private int number;

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString(){
        return "commodityId: " + commodityId + " salesRecordId: " + salesRecordId + " shopId: " + shopId + " date: " + date + " time: " + time + " number: " + number;
    }
}
