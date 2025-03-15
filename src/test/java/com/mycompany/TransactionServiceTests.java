package com.mycompany;

import com.mycompany.service.TransactionService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionServiceTests {
    @Autowired
    private TransactionService transactionService;
    private static BigDecimal accountBalanceChecker = BigDecimal.ZERO;
    private static final String newline = System.lineSeparator();

    @BeforeEach
    void setUp() {
        // reset account balance before each test if required
        // accountBalance = BigDecimal.ZERO
    }

    @Test
    void contextLoads() {
    }

    @Test
    @Order(2)
    void testDeposit_ValidAmount_ShouldIncreaseBalance() {
        BigDecimal amount = new BigDecimal("570.5");
        accountBalanceChecker = accountBalanceChecker.add(amount);
        boolean result = transactionService.deposit(amount);

        assertTrue(result);
        assertEquals(accountBalanceChecker, transactionService.getAccBalance());
    }

    @Test
    void testDeposit_NegativeAmounts_ShouldFail() {
        boolean result = transactionService.deposit(new BigDecimal("-100"));

        assertFalse(result);
        assertEquals(accountBalanceChecker, transactionService.getAccBalance());
    }

    @Test
    void testDeposit_MoreThan2dp_ShouldFail() {
        boolean result = transactionService.deposit(new BigDecimal("-300.505"));

        assertFalse(result);
        assertEquals(accountBalanceChecker, transactionService.getAccBalance());
    }

    @Test
    @Order(3)
    void testWithdraw_ValidAmount_ShouldReduceBalance() {
        BigDecimal amount = new BigDecimal("100");
        boolean result = transactionService.withdraw(amount);
        accountBalanceChecker = accountBalanceChecker.subtract(amount);

        assertTrue(result);
        assertEquals(accountBalanceChecker, transactionService.getAccBalance());
    }

    @Test
    void testWithdraw_InsufficientFunds_ShouldFail() {
        BigDecimal amount = transactionService.getAccBalance();
        amount = amount.add(BigDecimal.ONE);
        boolean result = transactionService.withdraw(amount);

        assertFalse(result);
        assertEquals(accountBalanceChecker, transactionService.getAccBalance());
    }

    @Test
    void testWithdraw_NegativeAmounts_ShouldFail() {
        boolean result = transactionService.withdraw(new BigDecimal("-100"));

        assertFalse(result);
        assertEquals(accountBalanceChecker, transactionService.getAccBalance());
    }

    @Test
    void testWithdraw_MoreThan2dp_ShouldFail() {
        boolean result = transactionService.withdraw(new BigDecimal("-300.505"));

        assertFalse(result);
        assertEquals(accountBalanceChecker, transactionService.getAccBalance());
    }

    @Test
    @Order(4)
    void testPrintStatement_HaveTransactions_ShouldReturnFormattedStatement() {
        //Date                   | Amount                | Balance
        //15 Mar 2025 05:11:51PM |                570.50 |                570.50
        //15 Mar 2025 05:11:51PM |               -100.00 |                470.50
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        transactionService.printStatement();

        // Currently test fail because of expectedOutput timestamp
        // need to find a way to handle
        String expectedOutput = "Date                   | Amount                | Balance              " + newline
                + "15 Mar 2025 05:11:51PM |                570.50 |                570.50" + newline
                + "15 Mar 2025 05:11:51PM |               -100.00 |                470.50" + newline
                + "Your statement has been printed.";

        assertEquals(expectedOutput, output.toString());
    }

    @Test
    // Test order for a success flow
    // Print empty statement > Deposit > Withdraw > Print Statement
    @Order(1)
    void testPrintStatement_NoTransactions_ShouldReturnStatement() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        transactionService.printStatement();
        String expectedOutput = "No statement is printed as there are no transactions under your account." + newline;

        assertEquals(expectedOutput, output.toString());
    }
}


