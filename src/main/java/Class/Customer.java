/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;


/**
 *
 * @author handikaharianto
 */
public class Customer extends User{

    private String firstName;
    private String lastName;
    private String passportNumber;
    private String phoneNumber;

    public Customer(String username, String password, String firstName, String lastName, String passportNumber, String phoneNumber) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
    } 
}
