package com.mycompany;

import com.mycompany.controller.TransactionController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        System.out.println("Starting the App");
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        TransactionController transactionController = context.getBean(TransactionController.class);
        transactionController.startBanking();
    }
}

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
// Press Alt+Enter with your caret at the highlighted text to see how
// IntelliJ IDEA suggests fixing it.