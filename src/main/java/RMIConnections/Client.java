package RMIConnections;


import java.net.*;
import java.rmi.*;

public class Client {
    public static Interface Object;
    public Client() throws RemoteException, NotBoundException, MalformedURLException {
        Object = (Interface) Naming.lookup("rmi://localhost:6969/DCOMS");
    }
}




