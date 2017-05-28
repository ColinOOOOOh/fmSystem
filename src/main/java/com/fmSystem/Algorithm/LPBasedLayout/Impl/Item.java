package com.fmSystem.Algorithm.LPBasedLayout.Impl;

/**
 * Created by 74551 on 2017/5/2.
 */
public class Item {
    private String itemName;
    private double profit;
    private double v;
    private double ma;
    private double mi;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getMa() {
        return ma;
    }

    public void setMa(double ma) {
        this.ma = ma;
    }

    public double getMi() {
        return mi;
    }

    public void setMi(double mi) {
        this.mi = mi;
    }

    @Override
    public String toString(){
        return "itemName: " + itemName + " profit: " + profit + " v: " + v + " ma: " + ma + " mi: " + mi;
    }
}
