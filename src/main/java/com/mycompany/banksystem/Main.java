package com.mycompany.banksystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static BankSystem bank = new BankSystem();

    public static void main(String[] args) {
        seedData();
        System.out.println("=== Welcome to the Simple Bank System ===");

        while (true) {
            System.out.println("Select role to login:");
            System.out.println("1. Customer");
            System.out.println("2. Employee");
            System.out.println("3. Manager");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": customerFlow(); break;
                case "2": employeeFlow(); break;
                case "3": managerFlow(); break;
                case "4": System.out.println("Goodbye."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void seedData() {
        Customer c1 = new Customer("CUST001", "alice", "alice@example.com", "pass123");
        Customer c2 = new Customer("CUST002", "bob", "bob@example.com", "pass456");
        bank.addCustomer(c1);
        bank.addCustomer(c2);

        Employee e1 = new Employee("EMP001", "eve", "eve@bank.com", "emp1", "E-01");
        bank.addEmployee(e1);

        Manager m1 = new Manager("MGR001", "mallory", "mallory@bank.com", "mgr1", "M-01");
        bank.addManager(m1);

        Account a1 = new SavingsAccount("A100", c1.getCustomerID(), 1000.0, 1.5, 3);
        Account a2 = new CurrentAccount("A200", c2.getCustomerID(), 500.0, 100.0);
        bank.addAccount(a1);
        bank.addAccount(a2);
    }

    // --- Customer flow ---
    private static void customerFlow() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        Customer c = bank.authenticateCustomer(username, password);
        if (c == null) { System.out.println("Login failed."); return; }

        System.out.println("Hello, " + c.getUserName());
        while (true) {
            System.out.println("Customer Menu:");
            System.out.println("1. View Accounts");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Request Card (stub)");
            System.out.println("6. Apply for Loan (stub)");
            System.out.println("7. Logout");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": bank.printAccountsForCustomer(c.getCustomerID()); break;
                case "2": handleDeposit(c); break;
                case "3": handleWithdraw(c); break;
                case "4": handleTransfer(c); break;
                case "5": System.out.println("Card request noted (stub)."); break;
                case "6": System.out.println("Loan application noted (stub)."); break;
                case "7": return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    // --- Employee flow ---
    private static void employeeFlow() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        Employee e = bank.authenticateEmployee(username, password);
        if (e == null) { System.out.println("Login failed."); return; }

        System.out.println("Hello, employee " + e.getEmployeeId());
        while (true) {
            System.out.println("Employee Menu:");
            System.out.println("1. View Assigned Customers (all shown)");
            System.out.println("2. Approve Transaction (by ID)");
            System.out.println("3. Create Account for Customer");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": bank.printAllCustomers(); break;
                case "2": {
                    System.out.print("Transaction ID to approve: ");
                    String tid = scanner.nextLine().trim();
                    Transaction tx = bank.findTransactionById(tid);
                    if (tx == null) System.out.println("Transaction not found.");
                    else if ("PENDING".equals(tx.getStatus())) {
                        tx.setStatus("APPROVED");
                        System.out.println("Transaction " + tid + " approved.");
                    } else System.out.println("Transaction not in pending state.");
                } break;
                case "3": {
                    System.out.print("Customer ID: ");
                    String cid = scanner.nextLine().trim();
                    System.out.print("New Account Number: ");
                    String accNo = scanner.nextLine().trim();
                    System.out.print("Initial Balance: ");
                    double bal = Double.parseDouble(scanner.nextLine().trim());
                    Account acc = new Account(accNo, bal);
                    acc.setOwnerId(cid);
                    bank.addAccount(acc);
                    System.out.println("Account created.");
                } break;
                case "4": return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    // --- Manager flow ---
    private static void managerFlow() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        Manager m = bank.authenticateManager(username, password);
        if (m == null) { System.out.println("Login failed."); return; }

        System.out.println("Hello, manager " + m.getManagerId());
        while (true) {
            System.out.println("Manager Menu:");
            System.out.println("1. View All Employees");
            System.out.println("2. Approve Loan (stub)");
            System.out.println("3. Generate Report");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": bank.printAllEmployees(); break;
                case "2": System.out.println("Loan approved (stub)."); break;
                case "3": bank.printReport(); break;
                case "4": return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    // --- Transaction handlers ---
    private static void handleDeposit(Customer c) {
        System.out.print("Account Number: ");
        String accNo = scanner.nextLine().trim();
        Account acc = bank.findAccountByNumberForCustomer(accNo, c.getCustomerID());
        if (acc == null) { System.out.println("Account not found or not yours."); return; }

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Method (Cash/Online/Check): ");
        String method = scanner.nextLine().trim();

        DepositTransaction dt = new DepositTransaction("DT" + System.currentTimeMillis(), amount, method);
        bank.addTransaction(dt);
        dt.processDeposit(acc);
    }

    private static void handleWithdraw(Customer c) {
        System.out.print("Account Number: ");
        String accNo = scanner.nextLine().trim();
        Account acc = bank.findAccountByNumberForCustomer(accNo, c.getCustomerID());
        if (acc == null) { System.out.println("Account not found or not yours."); return; }

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        WithdrawTransaction wt = new WithdrawTransaction("WT" + System.currentTimeMillis(), amount, 2000.0);
        bank.addTransaction(wt);
        wt.processWithdrawal(acc);
    }

    private static void handleTransfer(Customer c) {
        System.out.print("From Account Number: ");
        String from = scanner.nextLine().trim();
        Account fromAcc = bank.findAccountByNumberForCustomer(from, c.getCustomerID());
        if (fromAcc == null) { System.out.println("Account not found or not yours."); return; }

        System.out.print("To Account Number: ");
        String to = scanner.nextLine().trim();
        Account toAcc = bank.findAccountByNumber(to);
        if (toAcc == null) { System.out.println("Destination account not found."); return; }

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        TransferTransaction tt = new TransferTransaction("TT" + System.currentTimeMillis(), amount);
        bank.addTransaction(tt);
        tt.processTransfer(fromAcc, toAcc);
    }
}
