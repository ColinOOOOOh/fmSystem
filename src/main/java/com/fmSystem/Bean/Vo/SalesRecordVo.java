package com.fmSystem.Bean.Vo;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by 74551 on 2017/4/30.
 */
public class SalesRecordVo {
    private String commodityName;
    private Date date;
    private Time time;
    private int number;

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
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
    public String toString() {
        return "commodityName: " + commodityName + " date: " + date + " time: " + time + " number: " + number;
    }
}
