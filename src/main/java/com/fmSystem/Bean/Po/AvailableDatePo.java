package com.fmSystem.Bean.Po;

/**
 * Created by 74551 on 2017/4/28.
 */
public class AvailableDatePo {
    private int year;
    private int month;
    private int shopId;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public boolean isMatch(AvailableDatePo availableDatePo){
        if(availableDatePo.getYear() == this.year && availableDatePo.getMonth() == this.month){
            return true;
        }
        return false;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null ||getClass() != o.getClass()) return  false;
        AvailableDatePo availableDatePo = (AvailableDatePo) o;
        if (this.year == availableDatePo.getYear() && this.month ==availableDatePo.getMonth()) return  true;
        return false;
    }

    @Override
    public int hashCode(){
        return this.year * 100 + this.month;
    }
    @Override
    public String toString(){
        return "year: " + year + " month: " + month;
    }
}
