package com.smc.highlight.models;

public class UserModel {
    String username = "";
    String userEmail = "";
    String userImage = "";
    String userGender = "";
    String userbirthday = "";
    String userBelong = "";
    String userInterest = "";

    public UserModel(String username, String userEmail, String userImage, String userGender, String userbirthday, String userBelong, String userInterest) {
        this.username = username;
        this.userEmail = userEmail;
        this.userImage = userImage;
        this.userGender = userGender;
        this.userbirthday = userbirthday;
        this.userBelong = userBelong;
        this.userInterest = userInterest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserbirthday() {
        return userbirthday;
    }

    public void setUserbirthday(String userbirthday) {
        this.userbirthday = userbirthday;
    }

    public String getUserBelong() {
        return userBelong;
    }

    public void setUserBelong(String userBelong) {
        this.userBelong = userBelong;
    }

    public String getUserInterest() {
        return userInterest;
    }

    public void setUserInterest(String userInterest) {
        this.userInterest = userInterest;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
