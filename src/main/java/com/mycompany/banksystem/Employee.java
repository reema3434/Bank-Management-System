package com.mycompany.banksystem;
import java.util.List;


class Employee extends User {
    private String employeeId;
    public Employee(String userId, String username, String contactInfo, String password, String employeeId) {
        super(userId, username, contactInfo, password); this.employeeId = employeeId;
    }
    public String getEmployeeId() { return employeeId; }
}
