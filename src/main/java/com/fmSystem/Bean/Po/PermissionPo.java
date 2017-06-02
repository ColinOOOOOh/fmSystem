package com.fmSystem.Bean.Po;

/**
 * Created by 74551 on 2017/5/31.
 */
public class PermissionPo {
    private String permission;
    private int shopId;
    private int userId;

    public PermissionPo() {
    }

    public PermissionPo(String permission, int shopId, int userId) {
        this.permission = permission;
        this.shopId = shopId;
        this.userId = userId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
