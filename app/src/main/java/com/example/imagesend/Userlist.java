package com.example.imagesend;

public class Userlist {
    private String userID;
    private String userName;
    private String userPhone;
    private String imageProfile;


    public Userlist() {
    }

    public Userlist(String userID, String userName, String userPhone, String imageProfile) {
        this.userID = userID;
        this.userName = userName;
        this.userPhone = userPhone;
        this.imageProfile = imageProfile;

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

}