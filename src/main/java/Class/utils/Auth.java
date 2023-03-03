/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.utils;

import java.text.DecimalFormat;
import javax.swing.JTextField;

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

    public static boolean textFieldsFilled(JTextField... textFields) {
        for (JTextField textField : textFields) {
            if (textField == null || textField.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidUsername(String username) {
        // accept alphanumeric and underscore, with at least 5-15 characters
        return username.toLowerCase().matches("^[a-z][\\w]*$");
    }

    public static boolean usernameContainsLetters(String username) {
        // accept if there is a least one letter
        return username.toLowerCase().matches(".*[a-zA-Z].*");
    }

    public static boolean isValidUsernameLength(String username) {
        // accept only username with the length of 5-15 characters
        return username.length() > 4 && username.length() < 16;
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
        // accept alphanumeric and space, with at least 5-25 characters        
        return itemName.matches("^[a-zA-Z0-9\\s\\p{Punct}]{5,50}$");
    }

    public static boolean isValidUnitPrice(String unitPrice) {
        // accept any double greater than 0 with maximum two decimal points (eg 5.45)
        try {
            double price = Double.parseDouble(unitPrice);
            DecimalFormat df = new DecimalFormat("#.##");
            return Double.parseDouble(df.format(price)) == price && price > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidStockAmount(String stockAmount) {
        // accept any int with maximum 4 digits (1-9999)
        try {
            int amount = Integer.parseInt(stockAmount);
            return amount > 0 && amount < 10000;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean inputsChanged(
            String currentName, double currentPrice, int currentStock, 
            String originalName, double originalPrice, int originalStock) {
        return !currentName.equals(originalName) 
                || currentPrice != originalPrice 
                || currentStock != originalStock;
    }
    
   
    public static boolean isValidOrderQuantity(int availableQuantity, String userQuantity){
        
        try{
            int orderQuantity = Integer.valueOf(userQuantity);
            if(orderQuantity<=0 || availableQuantity-orderQuantity<0){
    
                return true;
            }else{
                return false;
            }    
        }catch(NumberFormatException e){
            return false;
        }}
    
    
    
    
}
