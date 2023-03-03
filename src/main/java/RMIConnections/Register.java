package RMIConnections;

import Class.utils.DerbyDB;
import java.rmi.*;
import java.rmi.registry.*;
import java.sql.SQLException;

//solely used to register port number, essentially creating a socket connection between server and client
public class Register {

    private final int portNumber;

    public Register() {
        this.portNumber = 6969;
    }

    public static void main(String[] args) throws RemoteException, SQLException {
        Register Reg = new Register();
        try {
            Registry socketclient = LocateRegistry.createRegistry(Reg.portNumber);
            System.out.println("Port registered at: " + Reg.portNumber);
            socketclient.rebind("DCOMS", new Server());
            System.out.println("Server is running...");
            
            //establish connection to server
            DerbyDB.connectDB();
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }
}