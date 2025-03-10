package com.mycompany;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // These 2 lines repeated
        Scanner promptUserInput = new Scanner(System.in);
        String newline = System.lineSeparator();

        MainMenu mainMenu = new MainMenu();

        mainMenu.startBanking();

    }
}

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
// Press Alt+Enter with your caret at the highlighted text to see how
// IntelliJ IDEA suggests fixing it.