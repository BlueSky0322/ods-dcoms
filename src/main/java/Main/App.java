package Main;

import RMIConnections.Client;
import UserInterface.AdminCRUDForm;
import UserInterface.AdminMainMenuForm;
import UserInterface.LoginForm;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author ryann
 */
public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Connection established with server.");
        Client client = new Client();
        
        //new LoginForm().setVisible(true);
        new AdminMainMenuForm().setVisible(true);
        
    }
}
