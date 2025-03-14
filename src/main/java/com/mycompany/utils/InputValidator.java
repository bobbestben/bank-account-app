package com.mycompany.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InputValidator {
    public static boolean isValidAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid amount entered. Amount must be greater than 0. Try again.");
            return false;
        }
        return true;
    }

    public static boolean isValidDecimalPlaces(BigDecimal amount) {
        if (amount.scale() > 2) {
            System.out.println("Invalid amount entered. Amount cannot be more than 2 decimal places. Try again.");
            return false;
        }
        return true;
    }
}
