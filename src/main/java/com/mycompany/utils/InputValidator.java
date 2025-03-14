package com.mycompany.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InputValidator {
    public static boolean isValidAmount(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
// validate the bankServices input
// deposit/withdraw only +ve
// only accept 2dp
