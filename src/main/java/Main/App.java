package Main;

import RMIConnections.Client;
import UserInterface.AdminCRUDForm;
import UserInterface.CustomerCartForm;
import UserInterface.LoginForm;
import UserInterface.CustomerPlaceOrderForm;

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
        
//        new AdminCRUDForm().setVisible(true);
        //new LoginForm().setVisible(false);
        new CustomerPlaceOrderForm().setVisible(true);
        //new CustomerCartForm().setVisible(true);
    }
}
