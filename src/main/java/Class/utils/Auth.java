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
    
}
