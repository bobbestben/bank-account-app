package com.mycompany;

import org.springframework.stereotype.Controller;

import java.util.Scanner;

//1. Refactor switch cases
//2. Change this to a view layer with W D P Q
@Controller
public class TransactionController {
    //private final TransactionService transactionService
    private final Scanner scanner;

    public TransactionController() {
        this.scanner = new Scanner(System.in);
    }

    String newline = System.lineSeparator();
//    Scanner promptUserInput = new Scanner(System.in);

    Deposit deposit = new Deposit();

    public void startBanking() {
        System.out.println(Constants.startBankingPrompt);
        // see if cna refactor this useroption thing
        //System.out.print("Input here: ");
        String userOption = scanner.nextLine();
        handleUserOption(userOption);
    }

    public void continueBanking() {
        System.out.println(Constants.continueBankingPrompt);
        //System.out.print("Input here: ");
        java.lang.String userOption = scanner.nextLine();
        handleUserOption(userOption);
    }

    public void handleDeposit() {}

    public void handleWithdraw() {}

    public void printAccStatement() {}

    public void handleUserOption(String userOption) {
        switch (userOption) {
            case "d" -> System.out.print("d");
            case "w" -> System.out.print("w");
            case "p" -> System.out.print("p");
            case "q" -> System.out.print("q");
            default -> System.out.print("asd");
        }
    }
}
