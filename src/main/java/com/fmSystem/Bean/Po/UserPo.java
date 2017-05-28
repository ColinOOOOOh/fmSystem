package com.fmSystem.Bean.Po;

/**
 * Created by 74551 on 2017/4/21.
 */
public class UserPo {
    private int userId;
    private String username;
    private String userPhone;
    private String userPassword;

    public UserPo(String userPhone, String userPassword){
        this.userPhone = userPhone;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "userId: " + userId + " username: " + username + " userPassword:" + userPassword + " userPhone: " + userPhone;
    }
}
