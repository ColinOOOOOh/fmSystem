package com.fmSystem.Bean.Po;

/**
 * Created by 74551 on 2017/4/25.
 */
public class UserShopPo {
    private int userId;
    private int shopId;
    private String permission;

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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString(){
        return "userId: " + userId + " shopId: " + shopId + " permission: " + permission;
    }


}
