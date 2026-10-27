package com.mycompany.banksystem;
import java.util.List;

public class Manager extends User {
    private String managerId;
    public Manager(String userId, String username, String contactInfo, String password, String managerId) {
        super(userId, username, contactInfo, password); this.managerId = managerId;
    }
    public String getManagerId() { return managerId; }
}
