package com.mycompany;

import java.util.Scanner;

public class MainMenu {

    Scanner promptUserInput = new Scanner(System.in);
    String newline = System.lineSeparator();

    Deposit deposit = new Deposit();

    public void startBanking() {
        System.out.println(Constants.startBanking);
        System.out.print("Input here: ");
        java.lang.String userOption = promptUserInput.nextLine();
        // Repeating scanner for user input

        switch (userOption) {
            case "d" -> deposit.startDeposit();
            case "w" -> System.out.print("w");
            case "p" -> System.out.print("p");
            case "q" -> System.out.print("q");
            default -> System.out.print("asd");
        }
        System.out.print("User selected:" + userOption);
    }

    public void continueBanking() {
        System.out.println(Constants.continueBankingPrompt);
        System.out.print("Input here: ");
        java.lang.String userOption = promptUserInput.nextLine();
        // Repeating scanner for user input

        switch (userOption) {
            case "d" -> deposit.startDeposit();
            case "w" -> System.out.print("w");
            case "p" -> System.out.print("p");
            case "q" -> System.out.print("q");
            default -> System.out.print("asd");
        }
        System.out.print("User selected:" + userOption);
    }
}
