package com.mycompany.banksystem;

class TransferTransaction extends Transaction {
private double transferFee;


public TransferTransaction(String transactionID, double amount) {
super(transactionID, amount);
this.transferFee = calculateTransferFees();
}


public void processTransfer(Account fromAccount, Account toAccount) {
double amount = getAmount();
double fees = calculateTransferFees();
double totalDeduction = amount + fees;


if (fromAccount.getBalance() < totalDeduction) { setStatus("FAILED");
System.out.println("Transfer failed: insufficient funds."); return; }


fromAccount.withdraw(totalDeduction);
toAccount.deposit(amount);
setStatus("COMPLETED");
System.out.println("Transfer completed: " + amount + " with fee " + fees);
}


public double calculateTransferFees() { return getAmount() * 0.05; }


public void reverseTransaction(Account fromAccount, Account toAccount) {
double amount = getAmount(); double fees = calculateTransferFees(); 
double totalDeduction = amount + fees;
if (toAccount.getBalance() < totalDeduction) 
{ setStatus("FAILED"); 
System.out.println("Failed to reverse: insufficient funds in destination."); 
return; }
fromAccount.deposit(amount); 
toAccount.withdraw(totalDeduction); setStatus("REVERSED");
}
}
