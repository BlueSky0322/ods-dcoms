package RMIConnections;


import java.net.*;
import java.rmi.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static Interface Object;
    public Client() {
        try {
            Object = (Interface) Naming.lookup("rmi://localhost:6969/DCOMS");
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}




