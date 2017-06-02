package com.fmSystem.Bean.Po;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by 74551 on 2017/5/30.
 */
public class RecordInfoPo {
    private int salesRecordId;
    private int recordInfoId;
    private int commodityId;
    private Date rDate;
    private Time rTime;
    private int number;

    public int getSalesRecordId() {
        return salesRecordId;
    }

    public void setSalesRecordId(int salesRecordId) {
        this.salesRecordId = salesRecordId;
    }

    public int getRecordInfoId() {
        return recordInfoId;
    }

    public void setRecordInfoId(int recordInfoId) {
        this.recordInfoId = recordInfoId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public Date getDate() {
        return rDate;
    }

    public void setDate(Date rDate) {
        this.rDate = rDate;
    }

    public Time getTime() {
        return rTime;
    }

    public void setTime(Time rTime) {
        this.rTime = rTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toString(){
        return "salesRecordId: " + salesRecordId + " recordInfoId: " + recordInfoId +
                " commodityId: " + commodityId + " date:" + rDate + " time: " + rTime + " number:" + number;
    }
}
