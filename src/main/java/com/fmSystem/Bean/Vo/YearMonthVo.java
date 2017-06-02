package com.fmSystem.Bean.Vo;

/**
 * Created by 74551 on 2017/6/1.
 */
public class YearMonthVo {
    private String year;
    private String month;

    public YearMonthVo() {
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString(){
        if (month.length() == 1){
            return year + "-0" + month;
        }
        return year + '-' + month;
    }
}
