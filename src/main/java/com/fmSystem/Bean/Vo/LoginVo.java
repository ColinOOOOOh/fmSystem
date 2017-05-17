package com.fmSystem.Bean.Vo;

/**
 * Created by 74551 on 2017/4/23.
 */
public class LoginVo {
    private String userPassword;
    private String username;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString(){
        return "userPassword: " + userPassword + " username: " + username;
    }
}
