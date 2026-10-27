package com.mycompany.banksystem;
class DepositTransaction extends Transaction {
private String depositMethod;
private boolean reversed;


public DepositTransaction(String transactionID, double amount, String depositMethod) {
super(transactionID, amount);
this.depositMethod = depositMethod;
this.reversed = false;
}


public void processDeposit(Account account) {
if (!account.isActive()) { System.out.println("Deposit denied: Account is inactive.");
setStatus("FAILED"); return; }
if (getAmount() <= 0) { System.out.println("Deposit failed: Invalid amount.");
setStatus("FAILED"); return; }
account.deposit(getAmount());
setStatus("COMPLETED");
System.out.println("Deposit processed. New Balance: " + account.getBalance());
}


public void reverseDeposit(Account account) {
if (reversed) { System.out.println("Reversal failed: Deposit already reversed."); 
setStatus("FAILED"); return; }
if (getAmount() > account.getBalance()) { System.out.println("Reversal failed: not enough funds."); 
setStatus("FAILED"); return; }
account.withdraw(getAmount());
reversed = true; setStatus("REVERSED");
}
}