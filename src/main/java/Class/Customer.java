/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.io.Serializable;


/**
 *
 * @author handikaharianto
 */
public class Customer implements Serializable {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String phoneNumber;

    public Customer(String username, String password, String firstName, String lastName, String passportNumber, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

   
}
