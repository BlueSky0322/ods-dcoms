package RMIConnections;


import java.net.*;
import java.rmi.*;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Interface Object = (Interface) Naming.lookup("rmi://localhost:6969/DCOMS");
    }
}




