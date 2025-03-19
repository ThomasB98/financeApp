package com.example.demo.Utils.Card;

import java.security.SecureRandom;
import java.time.LocalDate;

public class CardUtils {
    private static final SecureRandom random = new SecureRandom();

    // Generate a random 16-digit credit card number
    public static String generateCreditCardNumber() {
        StringBuilder cardNumber = new StringBuilder("4"); // Start with 4 (Visa-like number)
        for (int i = 1; i < 16; i++) {
            cardNumber.append(random.nextInt(10)); // Append random digits
        }
        return cardNumber.toString();
    }

    // Generate a valid till date (6 years from today)
    public static LocalDate generateValidTillDate() {
        return LocalDate.now().plusYears(6);
    }
}
