package com.mycompany;

import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

//restrict user format to 2dp - think can only do validation since is cmd line, not UI
//need to output 2 decimals
//see which functions can put to util
@Controller
public class TransactionController {
    //private final TransactionService transactionService
    private final Scanner scanner;

    public TransactionController() {
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
        System.out.println("Thank you. $" + formatTo2dp(amountToDeposit) + " has been deposited to your account.");
        continueBanking();
    }

    public void handleWithdraw() {
        System.out.println("Please enter the amount to withdraw:");
        BigDecimal amountToWithdraw = readUserInputAsMoney();
        System.out.println("Thank you. $" + formatTo2dp(amountToWithdraw) + " has been withdrawn.");
        continueBanking();
    }

    public void printAccStatement() {
    }

    public void promptAndHandleBankingService() {
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "d" -> handleDeposit();
            case "w" -> handleWithdraw();
            case "p" -> printAccStatement();
            case "q" -> System.out.print("q");
            default -> System.out.print("asd");
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
