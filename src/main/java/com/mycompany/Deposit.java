package com.mycompany;

import java.util.Scanner;

public class Deposit {
    public void startDeposit() {
        Scanner promptUserInput = new Scanner(System.in);
        String newline = System.lineSeparator();

        MainMenu mainMenu = new MainMenu();

        System.out.println("Please enter amount to deposit:");
        long amountToDeposit = promptUserInput.nextLong();
        System.out.println("Thank you. $" + amountToDeposit + " has been deposited to your account.");
        mainMenu.continueBanking();
    }
}
