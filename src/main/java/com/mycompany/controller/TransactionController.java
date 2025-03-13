package com.mycompany.controller;

import com.mycompany.constants.Constants;
import com.mycompany.service.TransactionService;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

//restrict user format to 2dp - think can only do validation since is cmd line, not UI
//need to output 2 decimals
//see which functions can put to util
@Controller
public class TransactionController {
    private final TransactionService transactionService;
    private final Scanner scanner;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
        this.scanner = new Scanner(System.in);
    }

    public void startBanking() {
        System.out.println(Constants.startBankingPrompt);
        promptAndHandleBankingService();
    }

    public void continueBanking() {
        System.out.println(Constants.continueBankingPrompt);
        promptAndHandleBankingService();
    }

    public void handleDeposit() {
        System.out.println("Please enter the amount to deposit:");
        BigDecimal amountToDeposit = readUserInputAsMoney();
        if (transactionService.deposit(amountToDeposit)) {
            System.out.println("Thank you. $" + formatTo2dp(amountToDeposit) + " has been deposited to your account.");
            continueBanking();
        }
    }

    public void handleWithdraw() {
        System.out.println("Please enter the amount to withdraw:");
        BigDecimal amountToWithdraw = readUserInputAsMoney();
        if (transactionService.withdraw(amountToWithdraw)) {
            System.out.println("Thank you. $" + formatTo2dp(amountToWithdraw) + " has been withdrawn.");
            continueBanking();
        }
    }

    public void printStatement() {
        transactionService.printStatement();
        System.out.println("Your statement has been printed");
        continueBanking();
    }

    public void promptAndHandleBankingService() {
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "d" -> handleDeposit();
            case "w" -> handleWithdraw();
            case "p" -> printStatement();
            case "q" -> System.exit(0);
            default -> {
                System.out.println("Invalid option selected. Please try again");
                promptAndHandleBankingService();
            }
        }
    }

    public String formatTo2dp(BigDecimal num) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(num);
    }

    // big decimal for precise calculations
    public BigDecimal readUserInputAsMoney() {
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume leftover new line, if not nextLine() will return empty string ("")
        return amount;
    }
}
