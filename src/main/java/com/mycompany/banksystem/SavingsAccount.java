package com.mycompany.banksystem;

class SavingsAccount extends Account {
private double interestRate;
private int monthlyWithdrawalLimit;
private int withdrawalsThisMonth;


public SavingsAccount(String accountNumber, String ownerId, double initialBalance, double interestRate, int monthlyWithdrawalLimit) {
super(accountNumber, initialBalance);
setOwnerId(ownerId);
this.interestRate = interestRate;
this.monthlyWithdrawalLimit = monthlyWithdrawalLimit;
this.withdrawalsThisMonth = 0;
}


public double calculateMonthlyInterest() {
return getBalance() * (interestRate / 100.0 / 12.0);
}


public void resetMonthlyWithdrawals() { withdrawalsThisMonth = 0; }


@Override
public boolean withdraw(double amount) {
if (withdrawalsThisMonth >= monthlyWithdrawalLimit) { System.out.println("Monthly withdrawal limit reached"); return false; }
boolean ok = super.withdraw(amount);
if (ok) withdrawalsThisMonth++;
return ok;
}
}