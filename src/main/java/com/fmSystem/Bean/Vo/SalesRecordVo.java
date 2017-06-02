package com.fmSystem.Bean.Vo;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by 74551 on 2017/4/30.
 */
public class SalesRecordVo {


    private int commodityId;
    private String commodityName;
    private Date rDate;
    private Time rTime;
    private int number;

    public SalesRecordVo() {
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

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    public Time getTime() {
        return rTime;
    }

    public void setTime(Time time) {
        this.rTime = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "commodityName: " + commodityName + " rDate: " + rDate + " time: " + rTime + " number: " + number;
    }
}
