package com.mycompany.banksystem;

import java.util.Scanner;
abstract class User {
    protected String userID;
    protected String username;
    protected String contactInfo;
    protected String password;

    public User() {}
    public User(String userID, String username, String contactInfo, String password) {
        this.userID = userID; this.username = username; this.contactInfo = contactInfo; this.password = password;
    }
    public String getUserID() { return userID; }
    public String getUserName() { return username; }
    public String getContactInfo() { return contactInfo; }
    public String getPassword() { return password; }
    public void setUserName(String name) { this.username = name; }
    public void setContactInfo(String info) { this.contactInfo = info; }
}