package com.mycompany.service;

import java.math.BigDecimal;

public interface TransactionService {
    boolean deposit(BigDecimal amount);

    boolean withdraw(BigDecimal amount);

    void printStatement();
}
