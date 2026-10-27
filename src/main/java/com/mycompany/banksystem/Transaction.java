package com.mycompany.banksystem;

import java.time.LocalDate;

abstract class Transaction {
private String transactionID;
private String date;
private double amount;
private String status;


public Transaction(String transactionID, double amount) {
this.transactionID = transactionID;
this.date = LocalDate.now().toString();
this.amount = amount;
this.status = "PENDING";
}


public String getTransactionID() { return transactionID; }
public String getDate() { return date; }
public double getAmount() { return amount; }
public String getStatus() { return status; }
public void setAmount(double amount) { this.amount = amount; }
public void setStatus(String status) { this.status = status; }


@Override
public String toString() {
return "Transaction{" + transactionID + ", date=" + date + ", amount=" + amount + ", status=" + status + '}';
}
}