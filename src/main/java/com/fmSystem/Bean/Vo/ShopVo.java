package com.fmSystem.Bean.Vo;

/**
 * Created by 74551 on 2017/6/1.
 */
public class ShopVo {
    private int userId;
    private int shopId;
    private String userPhone;

    public ShopVo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
