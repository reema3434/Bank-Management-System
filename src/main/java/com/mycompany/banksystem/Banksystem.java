package com.mycompany.banksystem;
import java.util.ArrayList;
import java.util.List;
// -----------------------------
// BankSystem class
// -----------------------------
class BankSystem {
    private ArrayList<Customer> customers = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Manager> managers = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public void addCustomer(Customer c) { customers.add(c); }
    public void addEmployee(Employee e) { employees.add(e); }
    public void addManager(Manager m) { managers.add(m); }
    public void addAccount(Account a) { accounts.add(a); }
    public void addTransaction(Transaction t) { transactions.add(t); }

    public Customer authenticateCustomer(String username, String password) {
        for (Customer c : customers) if (c.getUserName().equals(username) && c.getPassword().equals(password)) return c;
        return null;
    }
    public Employee authenticateEmployee(String username, String password) {
        for (Employee e : employees) if (e.getUserName().equals(username) && e.getPassword().equals(password)) return e;
        return null;
    }
    public Manager authenticateManager(String username, String password) {
        for (Manager m : managers) if (m.getUserName().equals(username) && m.getPassword().equals(password)) return m;
        return null;
    }

    public void printAccountsForCustomer(String customerId) {
        System.out.println("Accounts for customer " + customerId + ":");
        for (Account a : accounts) if (customerId.equals(a.getOwnerId())) a.printAccountDetails();
    }
    public void printAllCustomers() {
        System.out.println("--- Customers ---");
        for (Customer c : customers) System.out.println(c.getCustomerID() + " | " + c.getUserName() + " | " + c.getContactInfo());
    }
    public void printAllEmployees() {
        System.out.println("--- Employees ---");
        for (Employee e : employees) System.out.println(e.getEmployeeId() + " | " + e.getUserName() + " | " + e.getContactInfo());
    }
    public void printReport() {
        System.out.println("--- REPORT: Accounts ---");
        for (Account a : accounts) a.printAccountDetails();
        System.out.println("--- REPORT: Transactions ---");
        for (Transaction t : transactions) System.out.println(t);
    }
    public Transaction findTransactionById(String id) {
        for (Transaction t : transactions) if (t.getTransactionID().equals(id)) return t;
        return null;
    }
    public Account findAccountByNumber(String accNo) {
        for (Account a : accounts) if (a.getAccountNumber().equals(accNo)) return a;
        return null;
    }
    public Account findAccountByNumberForCustomer(String accNo, String customerId) {
        for (Account a : accounts) if (a.getAccountNumber().equals(accNo) && customerId.equals(a.getOwnerId())) return a;
        return null;
    }
}





