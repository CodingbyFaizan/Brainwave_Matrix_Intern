package com.projects.atm.system.daoimpl;

import com.projects.atm.system.dao.ATMServiceDao;
import com.projects.atm.system.dao.AccountDao;
import com.projects.atm.system.model.Account;

public class ATMService implements ATMServiceDao {
    private final AccountDao accountDAO = new AccountDaoImpl();

    public boolean validateAccount(String accountNumber, String pin) {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        return account != null && account.getPin().equals(pin);
    }

    public double checkBalance(String accountNumber) {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        return (account != null) ? account.getBalance() : -1;
    }

    public boolean deposit(String accountNumber, double amount) {
        if (amount <= 0)
            return false;
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if (account == null)
            return false;

        double newBalance = account.getBalance() + amount;
        return accountDAO.updateBalance(accountNumber, newBalance);
    }

    public boolean withdraw(String accountNumber, double amount) {
        if (amount <= 0)
            return false;
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if (account == null || account.getBalance() < amount)
            return false;

        double newBalance = account.getBalance() - amount;
        return accountDAO.updateBalance(accountNumber, newBalance);
    }
}
