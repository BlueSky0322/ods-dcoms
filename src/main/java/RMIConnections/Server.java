package RMIConnections;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements Interface {

    public Server() throws RemoteException {
        super();
    }

    @Override
    public void placeholderMethod() throws RemoteException {
        System.out.println("Hi");
    }

}
