package com.mycompany.controller;

import com.mycompany.constants.Constants;
import com.mycompany.service.TransactionService;
import com.mycompany.utils.InputValidator;
import com.mycompany.utils.Utils;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

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
                System.out.println("Thank you. $" + Utils.formatBigDecimalTo2Dp(amountToDeposit) + " has been deposited to your account.");
                break;
            }
        }
        continueBanking();
    }

    public void handleWithdraw() {
        while (true) {
            System.out.println("Please enter the amount to withdraw:");
            String userInput = scanner.nextLine();
            if (!InputValidator.isValidBigDecimal(userInput)) {
                continue;
            }
            BigDecimal amountToWithdraw = Utils.convertStringToBigDecimal(userInput);
            if (transactionService.withdraw(amountToWithdraw)) {
                System.out.println("Thank you. $" + Utils.formatBigDecimalTo2Dp(amountToWithdraw) + " has been withdrawn.");
                break;
            }
        }
        continueBanking();
    }

    public void printStatement() {
        transactionService.printStatement();
        continueBanking();
    }

    public void handleBankingService() {
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "d" -> handleDeposit();
            case "w" -> {
                boolean accountHasMoney = transactionService.getAccBalance().compareTo(BigDecimal.ZERO) > 0;
                if (accountHasMoney) {
                    handleWithdraw();
                } else {
                    System.out.println("Your account balance is $0.00. You are unable to perform withdrawals.");
                    continueBanking();
                }
            }
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
}
