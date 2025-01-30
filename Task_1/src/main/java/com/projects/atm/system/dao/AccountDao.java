package com.projects.atm.system.dao;

import com.projects.atm.system.model.Account;

public interface AccountDao {

    Account getAccountByNumber(String accountNumber);
    boolean updateBalance(String accountNumber, double newBalance);

}
