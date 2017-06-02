package com.fmSystem.Bean.Vo;

/**
 * Created by 74551 on 2017/5/31.
 */
public class RegisterVo {
    private String username;
    private String userPhone;
    private String userPassword;
    private String permission;

    public RegisterVo() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
