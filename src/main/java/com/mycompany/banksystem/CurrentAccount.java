package com.mycompany.banksystem;
class CurrentAccount extends Account {
private double overdraftLimit;


public CurrentAccount(String accountNumber, String ownerId, double initialBalance, double overdraftLimit) {
super(accountNumber, initialBalance);
setOwnerId(ownerId);
this.overdraftLimit = overdraftLimit;
}


public double getOverdraftLimit() { return overdraftLimit; }
public void setOverdraftLimit(double limit) { this.overdraftLimit = limit; }


@Override
public boolean withdraw(double amount) {
if (!isActive()) { System.out.println("Withdraw failed: account inactive");
return false; }
if (amount <= 0) { System.out.println("Invalid amount"); 
return false; }
if (getBalance() - amount < -overdraftLimit) { System.out.println("Overdraft limit exceeded");
return false; }
setBalance(getBalance() - amount);
System.out.println("Withdrawn " + amount + ", new balance " + getBalance());
return true;
}
}
