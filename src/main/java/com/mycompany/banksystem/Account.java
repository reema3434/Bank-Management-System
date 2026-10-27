package com.mycompany.banksystem;
public class Account {
    private String accountNumber;
    protected double balance;
    private boolean isActive;
    private String ownerId;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber; this.balance = initialBalance; this.isActive = true;
    }
    public Account() { this("000000", 0.0); }

    public void openAccount() { isActive = true; }
    public void closeAccount() { isActive = false; }
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public void setBalance(double amount) { this.balance = amount; }
    public boolean isActive() { return isActive; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
    public String getOwnerId() { return ownerId; }

    public void deposit(double amount) {
        if (!isActive) { System.out.println("Deposit failed: account inactive"); return; }
        if (amount <= 0) { System.out.println("Invalid deposit amount"); return; }
        balance += amount; System.out.println("Deposited " + amount + ", new balance " + balance);
    }
    public void deposit(double amount, String note) { deposit(amount); System.out.println("Note: " + note); }

    public boolean withdraw(double amount) {
        if (!isActive) { System.out.println("Withdraw failed: account inactive"); return false; }
        if (amount <= 0 || amount > balance) { System.out.println("Insufficient funds or invalid amount"); return false; }
        balance -= amount; System.out.println("Withdrawn " + amount + ", new balance " + balance); return true;
    }

    public void printAccountDetails() {
        System.out.println("--- Account " + accountNumber + " ---");
        System.out.println("Owner: " + ownerId);
        System.out.println("Balance: " + balance);
        System.out.println("Active: " + isActive);
    }
}