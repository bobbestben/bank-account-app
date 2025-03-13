package com.mycompany.service;

import com.mycompany.models.Account;
import com.mycompany.models.Transaction;
import com.mycompany.utils.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final Account account;

    @Autowired
    public TransactionServiceImpl(Account account) {
        this.account = account;
    }

    @Override
    public boolean deposit(BigDecimal amount) {
        if (!InputValidator.isValidAmount(amount)) {
            System.out.println("Invalid amount entered for deposit");
            return false;
        }
        account.setBalance(account.getBalance().add(amount));
        account.addTransaction(new Transaction(amount, account.getBalance()));
        return true;
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        if (!InputValidator.isValidAmount(amount)) {
            System.out.println("Invalid amount entered for withdrawal");
            return false;
        }
        if (amount.compareTo(account.getBalance()) > 0) {
            System.out.println("Insufficient funds for withdrawal");
            return false;
        }
        account.setBalance(account.getBalance().subtract(amount));
        account.addTransaction(new Transaction(amount.multiply(BigDecimal.valueOf(-1)),
                account.getBalance()));
        return true;
    }

//    @Override
//    public BigDecimal checkBalance() {
//        return null;
//    }

    @Override
    public void printStatement() {
        // Sample Statement:
        // Date                  | Amount  | Balance
        // 8 Jul 2022 11:12:30AM | 500.00  | 500.00
        // 8 Jul 2022 11:14:15AM | -100.00 | 400.00
        System.out.println("Date                  | Amount  | Balance");
        // need account for condition where -ve +ve amount, extra space
        for (Transaction transaction : account.getTransactions()) {
            System.out.printf("%s | %s  | %s%n",
                    transaction.getTimestamp(),
                    transaction.getAmount(),
                    transaction.getBalance());
        }
    }
}
