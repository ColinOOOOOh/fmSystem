package com.fmSystem.Bean.Vo;

/**
 * Created by 74551 on 2017/4/23.
 */
public class LoginVo {
    private String userPassword;
    private String userPhone;

    public LoginVo() {
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString(){
        return "userPassword: " + userPassword + " userPhone: " + userPhone;
    }
}
