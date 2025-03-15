package com.mycompany.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Utils {
    public static String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm:ssa");
        String formattedDateWithoutCaps = date.format(formatter);
        // capitalize the last 2 char "am/pm" to "AM/PM"
        return formattedDateWithoutCaps.substring(0, formattedDateWithoutCaps.length() - 2)
                + formattedDateWithoutCaps.substring(formattedDateWithoutCaps.length() - 2).toUpperCase();
    }

    public static String formatBigDecimalTo2Dp(BigDecimal amount) {
        return String.format("%,.2f", amount);
    }

    public static BigDecimal convertStringToBigDecimal(String string) {
        return new BigDecimal(string);
    }
}
