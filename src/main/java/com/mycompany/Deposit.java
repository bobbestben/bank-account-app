package com.mycompany;

import java.util.Scanner;

public class Deposit {
    public void startDeposit() {
        Scanner promptUserInput = new Scanner(System.in);
        String newline = System.lineSeparator();

        TransactionController transactionController = new TransactionController();

        System.out.println("Please enter amount to deposit:");
        long amountToDeposit = promptUserInput.nextLong();
        System.out.println("Thank you. $" + amountToDeposit + " has been deposited to your account.");
        transactionController.continueBanking();
    }
}
