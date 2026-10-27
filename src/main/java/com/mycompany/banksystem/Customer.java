package com.mycompany.banksystem;
public class Customer extends User {
    private String customerID;
    public Customer(String customerID, String username, String contactInfo, String password) {
        super(customerID, username, contactInfo, password); this.customerID = customerID;
    }
    public String getCustomerID() { return customerID; }
}
