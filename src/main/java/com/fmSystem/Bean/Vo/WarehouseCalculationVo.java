package com.fmSystem.Bean.Vo;

import java.sql.Date;

/**
 * Created by 74551 on 2017/5/21.
 */
public class WarehouseCalculationVo {
    private int number;
    private Date date;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString(){
        return "number= " + number + " date= " + date;
    }
}
