/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author handikaharianto
 */
public class Hasher {
    
    public static String sha256(String input) {
        return hash(input, "SHA-256");
    }
    
    private static String hash(String input, String algorithm) {
        String hashCode = "";

        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(input.getBytes());
            // digesting...            
            byte[] hashBytes = md.digest();
            // convert the byte array to string
            hashCode = Base64.getEncoder().encodeToString(hashBytes);
            // hex format output
            hashCode = Hex.encodeHexString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }      
        return hashCode;
    }
}
