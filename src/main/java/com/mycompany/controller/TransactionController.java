package com.mycompany.controller;

import com.mycompany.constants.Constants;
import com.mycompany.service.TransactionService;
import com.mycompany.utils.InputValidator;
import com.mycompany.utils.Utils;
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
        handleBankingService();
    }

    public void continueBanking() {
        System.out.println(Constants.continueBankingPrompt);
        handleBankingService();
    }

    public void handleDeposit() {
        while (true) {
            System.out.println("Please enter the amount to deposit:");
            String userInput = scanner.nextLine();
            if (!InputValidator.isValidBigDecimal(userInput)) {
                continue;
            }
            BigDecimal amountToDeposit = Utils.convertStringToBigDecimal(userInput);
            if (transactionService.deposit(amountToDeposit)) {
                System.out.println("Thank you. $" + formatTo2dp(amountToDeposit) + " has been deposited to your account.");
                break;
            }
        }
        continueBanking();
    }

    public void handleWithdraw() {
        System.out.println("Please enter the amount to withdraw:");
        BigDecimal amountToWithdraw = readUserInputAndConvertToBigDecimal();
        if (transactionService.withdraw(amountToWithdraw)) {
            System.out.println("Thank you. $" + formatTo2dp(amountToWithdraw) + " has been withdrawn.");
            continueBanking();
        } else {
            handleWithdraw();
        }
    }

    public void printStatement() {
        transactionService.printStatement();
        continueBanking();
    }

    public void handleBankingService() {
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "d" -> handleDeposit();
            case "w" -> handleWithdraw();
            case "p" -> printStatement();
            case "q" -> {
                System.out.print(Constants.thankYouMessage);
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid option selected. Please try again.");
                handleBankingService();
            }
        }
    }

    public String formatTo2dp(BigDecimal num) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(num);
    }

    // big decimal for precise calculations
    public BigDecimal readUserInputAndConvertToBigDecimal() {
        String amount_string = scanner.nextLine();
//        convertUserInputToBigDecimal()
        BigDecimal amount_bigDecimal = null;
        try {
            amount_bigDecimal = new BigDecimal(amount_string);
        } catch (Exception e) {

        }
        return amount_bigDecimal;
    }
}
