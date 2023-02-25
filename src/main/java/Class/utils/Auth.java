/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.utils;

/**
 *
 * @author handikaharianto
 */
public class Auth {

    public static boolean inputFieldsFilled(String... inputs) {
        for (String input : inputs) {
            if (input.isBlank()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidUsername(String username) {
        // accept alphanumeric and underscore, with at least 5-15 characters
        return username.toLowerCase().matches("^(\\w){5,15}$");
    }

    public static boolean isValidPassport(String passport) {
        // accept first letter, then followed by 8 numbers
        return passport.toUpperCase().matches("^[A-Z][0-9]{8}$");
    }

    public static boolean passwordsMatched(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public static boolean isValidPassword(String password) {
        // accept password with at least 8 characters
        return password.length() > 7;
    }

    public static boolean isValidItemName(String itemName) {
        // accept alphanumeric and underscore, with at least 5-25 characters
        return itemName.toLowerCase().matches("^(\\w){5,25}$");
    }

    public static boolean isValidUnitPrice(String unitPrice) {
        // accept double with maximum 2 decimal points
        try {
            double value = Double.parseDouble(unitPrice);
            return Math.abs(value - Math.floor(value)) == 0.0 || unitPrice.matches("^\\d+\\.\\d{2}$");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidStockAmount(String stockAmount) {
        // accept any int with maximum 4 digits (0-9999)
        try {
            int value = Integer.parseInt(stockAmount);
            return value >= 0 && value < 10000;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
