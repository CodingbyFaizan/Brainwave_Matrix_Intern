package com.projects.atm.system.dao;

public interface ATMServiceDao {

    boolean validateAccount(String accountNumber, String pin);
    double checkBalance(String accountNumber);
    boolean deposit(String accountNumber, double amount);
    public boolean withdraw(String accountNumber, double amount);


}
