package com.mycompany.service;

import com.mycompany.models.Account;
import com.mycompany.models.Transaction;
import com.mycompany.utils.InputValidator;
import com.mycompany.utils.Utils;
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
            return false;
        }
        if (!InputValidator.isValidDecimalPlaces(amount)) {
            return false;
        }
        account.setBalance(account.getBalance().add(amount));
        account.addTransaction(new Transaction(amount, account.getBalance()));
        return true;
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        if (!InputValidator.isValidAmount(amount)) {
            return false;
        }
        if (!InputValidator.isValidDecimalPlaces(amount)) {
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

    @Override
    public void printStatement() {
        if (account.getTransactions().isEmpty()) {
            System.out.println("No statement is printed as there are no transactions under your account.");
            return;
        }
        // Trailing spaces might vary
        // Have to account for large numbers, negative value
        // Right align numbers for readability
        // Sample Statement
        // Date                   | Amount                | Balance
        // 15 Mar 2025 02:32:31AM |      1,000,000,000.00 |      1,000,000,000.00
        // 15 Mar 2025 02:32:33AM |           -100,000.00 |        999,900,000.00

        System.out.printf("%-22s | %-21s | %-21s%n",
                "Date", "Amount", "Balance"
        );
        for (Transaction transaction : account.getTransactions()) {
            System.out.printf("%22s | %21s | %21s%n",
                    Utils.formatDate(transaction.getTimestamp()),
                    Utils.formatBigDecimalTo2Dp(transaction.getAmount()),
                    Utils.formatBigDecimalTo2Dp(transaction.getBalance())
            );
        }
        System.out.println("Your statement has been printed.");
    }

    @Override
    public BigDecimal getAccBalance() {
        return account.getBalance();
    }
}
