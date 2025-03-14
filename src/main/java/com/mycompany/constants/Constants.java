package com.mycompany.constants;

public class Constants {

    static String newline = System.lineSeparator();

    public static String bankServicesPrompt =
            "[D]eposit" + newline
                    + "[W]ithdraw" + newline
                    + "[P]rint Statement" + newline
                    + "[Q]uit";

    public static String startBankingPrompt =
            "Welcome to AwesomeGIC Bank! What would you like to do?" + newline
                    + bankServicesPrompt;

    public static String continueBankingPrompt =
            "Is there anything else you'd like to do?" + newline
                    + bankServicesPrompt;

    public static String userInputPrompt = "Input here: ";

    public static String thankYouMessage = "Thank you for banking with AwesomeGIC Bank." + newline +
            "Have a nice day!";
}
