package com.mycompany.banksystem;
class WithdrawTransaction extends Transaction {
private double dailyLimit;
private double amountWithdrawnToday;


public WithdrawTransaction(String transactionID, double amount, double dailyLimit) {
super(transactionID, amount);
this.dailyLimit = dailyLimit;
this.amountWithdrawnToday = 0;
}


public boolean checkDailyLimit() {
return (amountWithdrawnToday + getAmount()) <= dailyLimit;
}


public void processWithdrawal(Account account) {
if (!account.isActive()) { System.out.println("Withdrawal denied: Account is inactive."); setStatus("FAILED"); return; }
if (!checkDailyLimit()) { System.out.println("Daily limit exceeded."); setStatus("FAILED"); return; }
if (!account.withdraw(getAmount())) { setStatus("FAILED"); return; }
amountWithdrawnToday += getAmount(); setStatus("COMPLETED");
System.out.println("Withdrawal completed.");
}


public void reverseWithdrawal(Account account) {
if (!account.isActive()) { System.out.println("Reversal failed: Account inactive."); 
setStatus("FAILED");
return; }
account.deposit(getAmount());
amountWithdrawnToday -= getAmount(); setStatus("REVERSED");
System.out.println("Withdrawal reversed.");
}
}