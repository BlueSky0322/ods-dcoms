package RMIConnections;


import Class.utils.DerbyDB;
import java.rmi.*;
import java.rmi.registry.*;

//solely used to register port number, essentially creating a socket connection between server and client
public class Register {

    private final int portNumber;

    public Register() {
        this.portNumber = 6969;
    }

    public static void main(String[] args) throws RemoteException {
        Register Reg = new Register();
        try {
            Registry socketclient = LocateRegistry.createRegistry(Reg.portNumber);
            System.out.println("Port registered at: " + Reg.portNumber);
            socketclient.rebind("DCOMS", new Server());
            System.out.println("Server is running...");
            
            DerbyDB.connectDB();
            System.out.println("Database is running...");
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }
}
