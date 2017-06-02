package com.fmSystem.Bean.Vo;

/**
 * Created by 74551 on 2017/6/1.
 */
public class RecordVo {
    private int commodityId;
    private String commodityName;
    private String rDate;
    private String rTime;
    private int number;
    private int recordInfoId;

    public RecordVo() {
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

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
