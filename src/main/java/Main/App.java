package Main;

import RMIConnections.Client;
import UserInterface.LoginForm;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author ryann
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Connection established with server.");
        //Testing GitHub
        Client client = new Client();
        
        new LoginForm().setVisible(true);
    }
}
