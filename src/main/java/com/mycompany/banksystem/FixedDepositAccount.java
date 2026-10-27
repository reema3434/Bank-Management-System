package com.mycompany.banksystem;
import java.time.LocalDate;


class FixedDepositAccount extends Account {
private double principalAmount;
private LocalDate maturityDate;
private double interestRate;


public FixedDepositAccount(String accountNumber, String ownerId, double principal, LocalDate maturityDate, double interestRate) {
super(accountNumber, principal);
setOwnerId(ownerId);
this.principalAmount = principal;
this.maturityDate = maturityDate;
this.interestRate = interestRate;
}


public double calculateMaturityAmount() {
double rate = interestRate / 100.0;
return getBalance() + (getBalance() * rate);
}


@Override
public boolean withdraw(double amount) {
System.out.println("Use closeBeforeMaturity() to close a fixed deposit account.");
return false;
}


public void closeBeforeMaturity(double penaltyAmount) {
if (penaltyAmount < 0) throw new IllegalArgumentException("Penalty cannot be negative.");
if (penaltyAmount > getBalance()) penaltyAmount = getBalance();
setBalance(getBalance() - penaltyAmount);
System.out.println("Fixed deposit closed before maturity. Penalty: " + penaltyAmount);
}
}